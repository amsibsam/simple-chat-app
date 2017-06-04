package android.rahardyan.simplechatapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * Created by rahardyan on 16/05/17.
 */

/*
 * ConnectivityUtils: Connectivity related utilities to check if device is connected to network.
 */
public final class ConnectivityUtils {
    private static final String TAG = ConnectivityUtils.class.getSimpleName ();

    private ConnectivityUtils() {
    }

    /**
     * Check if the device is connected to Mobile network!
     *
     * @param context - App Context
     * @return - true, if connected to Mobile network, false otherwise
     */
    public static boolean isConnectedToMobile (Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if(VersionUtil.isLollipopOrHigher()) {
            Network[] networks = conMan.getAllNetworks();
            for(Network network:networks) {
                if(conMan.getNetworkInfo(network)!= null &&
                        conMan.getNetworkInfo(network).getType() == ConnectivityManager.TYPE_MOBILE){
                    networkInfo = conMan.getNetworkInfo(network);
                    break;
                }
            }
        } else {
            networkInfo = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        }


        return networkInfo == null ? false : (networkInfo.getState() == NetworkInfo.State.CONNECTED);
    }

    /**
     * Check if the device is connected to WiFi
     *
     * @param context - App Context
     * @return - true, if connected to WiFi, false otherwise
     */
    public static boolean isConnectedToWifi (Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if(VersionUtil.isLollipopOrHigher()) {
            Network[] networks = conMan.getAllNetworks();
            for(Network network:networks) {
                if(conMan.getNetworkInfo(network)!= null &&
                        conMan.getNetworkInfo(network).getType() == ConnectivityManager.TYPE_WIFI){
                    networkInfo = conMan.getNetworkInfo(network);
                    break;
                }
            }
        } else {
            networkInfo = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        }

        return networkInfo == null ? false : (networkInfo.getState() == NetworkInfo.State.CONNECTED);
    }

    /**
     * Check if the device is connected to ANY data network!
     *
     * @param context - App Context
     * @return - true, if connected to ANY data network, false otherwise
     */
    public static boolean isConnectedToNetwork (Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo ();
        boolean isConnected = netInfo != null && netInfo.isConnected ();
        return isConnected;
    }
}