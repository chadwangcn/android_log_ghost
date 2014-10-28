package com.youku.logcatservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
	 
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent();
        myIntent.setAction("com.youku.logcatservice.LogcatService");
        context.startService(myIntent);
    }
}