package com.shaily.notifapp.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.shaily.notifapp.SplashScreen;

public class AppReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("Reciever ","Recoeved");
       /* Intent i = context.getPackageManager().getLaunchIntentForPackage("com.shaily.notifapp");
        Intent intents = new Intent(context, SplashScreen.class);
        context.startActivity(i);*/

        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {

            Intent activityIntent = new Intent(context, SplashScreen.class);

            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(activityIntent);
        }
    }
}