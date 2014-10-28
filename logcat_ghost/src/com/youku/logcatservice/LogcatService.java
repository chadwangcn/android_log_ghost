package com.youku.logcatservice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.Service;  
import android.content.Intent;  
import android.os.Binder;  
import android.os.IBinder;  
import android.util.Log;

public class LogcatService extends Service {

	private boolean started;
	
	private ServiceBinder serviceBinder = new ServiceBinder();
	
	
	public class ServiceBinder extends Binder implements interface_service {

        @Override
        public boolean isStarted() {
            return started;
        }

        @Override
        public void start() {
            started=true;
            Log.d("logcat.service","logcat start");
        }  

        @Override
        public void stop() {
            started=false;
            Log.d("logcat.service","logcat stop");
        }
    }
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return serviceBinder;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();

		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						if (started) {
							Log.d("logcat.service", "send a sms message.");
							Thread.sleep(1000 * 2);
						}
						else
						{
							Log.d("logcat.service", "not started");
							Thread.sleep(1000 * 2);
						}
						
					} catch (InterruptedException e) {
						
					}
				}
			}
		};

		thread.start();

		Log.d("logcat.service", "logcat service created.");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("logcat.service", "logcat service shutdown.");
	}

}
