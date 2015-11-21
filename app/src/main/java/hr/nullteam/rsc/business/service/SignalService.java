package hr.nullteam.rsc.business.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

public class SignalService extends Service {

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
        Log.e("Service", "Start service");
        Toast.makeText(this, "Service Start", Toast.LENGTH_LONG).show();

        String server = "http://rschackathonvz.azurewebsites.net";
        HubConnection connection = new HubConnection(server);
        HubProxy proxy = connection.createHubProxy("ChatHub");

        proxy.invoke("JoinGroup", "Group1");

        //Then call on() to handle the messages when they are received.
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

        proxy.invoke("Send", "Android", "ping + " + (new Date()).getTime());



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
