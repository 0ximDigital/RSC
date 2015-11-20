package hr.nullteam.rsc.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

public final class NetworkUtils {

    private final ConnectivityManager connectivityManager;

    @Inject
    public NetworkUtils(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    public boolean isConnected(){
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
