package hr.nullteam.rsc.business.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.zsoft.signala.hubs.HubConnection;
import com.zsoft.signala.hubs.HubInvokeCallback;
import com.zsoft.signala.hubs.HubOnDataCallback;
import com.zsoft.signala.hubs.IHubProxy;
import com.zsoft.signala.transport.StateBase;
import com.zsoft.signala.transport.longpolling.LongPollingTransport;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hr.nullteam.rsc.application.RscApplication;

public class SignalService extends Service {

    public static final String JOIN_MATCH = "JOIN_GROUP";
    public static final String JOIN_TEAM = "JOIN_MATCH";

    protected HubConnection con = null;
    protected IHubProxy hub = null;

    @Inject
    Bus bus;

    public SignalService() {
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, SignalService.class);
    }

    public static Intent getJoinMatchIntent(Context context, long matchId) {
        Intent intent = new Intent(context, SignalService.class);
        intent.setAction(JOIN_MATCH);
        intent.putExtra(JOIN_MATCH, matchId);
        return intent;
    }

    public static Intent getjoinTeamIntent(Context context, long teamId) {
        Intent intent = new Intent(context, SignalService.class);
        intent.setAction(JOIN_TEAM);
        intent.putExtra(JOIN_TEAM, teamId);
        return intent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        injectMe();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(hub == null) {
            initialiseHub();
        }
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            if (JOIN_MATCH.equals(action)) {
                joinGame(intent.getLongExtra(JOIN_MATCH, -1));
            } else if (JOIN_TEAM.equals(action)) {
                joinTeam(intent.getLongExtra(JOIN_TEAM, -1));
            }
        }
        return START_NOT_STICKY;
    }

    private void injectMe() {
        ((RscApplication) getApplication()).getApplicationComponent().inject(this);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        initialiseHub();

    }

    private void initialiseHub() {
        HubInvokeCallback callback = new HubInvokeCallback() {
            @Override
            public void OnResult(boolean succeeded, String response) {
                Toast.makeText(SignalService.this, response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnError(Exception ex) {
                Toast.makeText(SignalService.this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        Connect(Uri.parse("http://rschackathonvz.azurewebsites.net/"));
    }

    public void Connect(Uri address) {

        con = new HubConnection(address.toString(), this, new LongPollingTransport()) {
            @Override
            public void OnStateChanged(StateBase oldState, StateBase newState) {

                switch (newState.getState()) {
                    case Connected:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void OnError(Exception exception) {
                Toast.makeText(SignalService.this, "On error: " + exception.getMessage(), Toast.LENGTH_LONG).show();
            }

        };

        try {
            hub = con.CreateHubProxy("ChatHub");
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }

        hub.On("addNewMessageToPage", new HubOnDataCallback() {
            @Override
            public void OnReceived(JSONArray args) {
                for (int i = 0; i < args.length(); i++) {
                    Toast.makeText(SignalService.this, "New message\n" + args.opt(i).toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        con.Start();
    }

    private void joinTeam(long teamId) {
        HubInvokeCallback callback = new HubInvokeCallback() {
            @Override
            public void OnResult(boolean succeeded, String response) {
                if (succeeded) {
                    Toast.makeText(SignalService.this, "Joined group", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignalService.this, "Failed to join group", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnError(Exception ex) {
                Toast.makeText(SignalService.this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        Log.d("TAG", "joining team -> " + teamId);

        List<String> args = new ArrayList<String>(1);
        args.add(String.valueOf(teamId));
        hub.Invoke("JoinGroupTeam", args, callback);
    }

    private void joinGame(long matchId) {
        HubInvokeCallback callback = new HubInvokeCallback() {
            @Override
            public void OnResult(boolean succeeded, String response) {
                if (succeeded) {
                    Toast.makeText(SignalService.this, "Joined group", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignalService.this, "Failed to join group", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnError(Exception ex) {
                Toast.makeText(SignalService.this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        List<String> args = new ArrayList<String>(1);
        args.add(String.valueOf(matchId));
        hub.Invoke("JoinGroupGame", args, callback);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "destroy service");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
