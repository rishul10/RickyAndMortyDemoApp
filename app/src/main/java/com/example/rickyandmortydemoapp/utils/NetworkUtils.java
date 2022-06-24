package com.example.rickyandmortydemoapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {
    public static boolean isInternet(Context context) {

        if (context == null) return false;

        boolean isInternet = false;

        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (con.getNetworkInfo(0) != null && con.getNetworkInfo(1) != null) {

            if (con.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
                    || con.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING
                    || con.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED
                    || con.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING) {
                isInternet = true;

            } else if (con.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
                    || con.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
                isInternet = false;

            }
        } else if (con.getNetworkInfo(0) != null) {

            if (con.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
                    || con.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING) {
                isInternet = true;

            } else if (con.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED) {
                //Toast.makeText(context, "No network", Toast.LENGTH_LONG).show();
                isInternet = false;

            }
        } else if (con.getNetworkInfo(1) != null) {

            if (con.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED
                    || con.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING) {
                isInternet = true;

            } else if (con.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
                isInternet = false;

            }
        }
        return isInternet;
    }
}
