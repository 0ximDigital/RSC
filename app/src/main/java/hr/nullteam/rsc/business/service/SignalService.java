package hr.nullteam.rsc.business.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.zsoft.signala.hubs.HubConnection;
import com.zsoft.signala.hubs.HubInvokeCallback;
import com.zsoft.signala.hubs.HubOnDataCallback;
import com.zsoft.signala.hubs.IHubProxy;
import com.zsoft.signala.transport.StateBase;
import com.zsoft.signala.transport.longpolling.LongPollingTransport;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class SignalService extends Service {

    private Handler mHandler;

    private final Context mContext = this;

    protected HubConnection con = null;
    protected IHubProxy hub = null;

    public SignalService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service", "Create service");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        /*Platform.loadPlatformComponent(new AndroidPlatformComponent());
        Log.e("Service", "Start service");
        Toast.makeText(this, "Service Start", Toast.LENGTH_LONG).show();

        String server = "http://rschackathonvz.azurewebsites.net/signalr";
        HubConnection connection = new HubConnection(server);
        HubProxy proxy = connection.createHubProxy("ChatHub");

        proxy.invoke("JoinGroup", "Group1");

        mHandler = new Handler(Looper.getMainLooper());

        //Then call on() to handle the messages when they are received.

        proxy.on("addNewMessageToPage", new SubscriptionHandler1<String>() {
            @Override
            public void run(final String s) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "message recieved: " + s, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }, String.class);

        proxy.on("addNewMessageToPage", msg -> {
            Log.d("result := ", msg);
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }, String.class);

        SignalRFuture<Void> awaitConnection = connection.start();
        try {
            awaitConnection.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        proxy.invoke("Send", "Android", "ping + " + (new Date()).getTime());*/

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

        List<String> args = new ArrayList<String>(2);
        args.add("Message");
        args.add("Android");
        hub.Invoke("Send", args, callback);

    }

    public void Connect(Uri address) {

        con = new HubConnection(address.toString(), this, new LongPollingTransport())
        {
            @Override
            public void OnStateChanged(StateBase oldState, StateBase newState) {
                //tvStatus.setText(oldState.getState() + " -> " + newState.getState());

                switch(newState.getState())
                {
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
