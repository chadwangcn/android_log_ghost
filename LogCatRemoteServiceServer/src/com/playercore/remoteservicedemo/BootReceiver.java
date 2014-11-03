package com.playercore.remoteservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
	 
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent();
        Log.v("boot","receive boot ");
        myIntent.setAction("com.playercore.remoteservicedemo.LogGhostService");
        context.startService(myIntent);
    }
}