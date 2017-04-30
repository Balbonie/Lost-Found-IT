package com.example.android.androidclass.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by josep on 3/1/2017.
 */
public class ConnectionHelper {
    public static boolean verifyConnection(Context context)
    {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo !=null && networkInfo.isConnected())
        {
            System.out.println(networkInfo.toString());
            System.out.println("Connection OK");
            return true;
        }
        System.out.println("Connection NOT OK");
        return false;
    }

}
