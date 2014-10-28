package com.playercore.remoteservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class LogGhostService extends Service {
	private static final String TAG = "LogService";
	private boolean started;
	private Thread work_thread = null;
	
	
	public class LogGhostServiceImpl extends ILogGhostService.Stub{

		@Override
		public boolean isStarted() throws RemoteException {
			
			return started;
		}
		
		@Override
		public void start() throws RemoteException {
				
			Log.d(TAG, "start");
			
		}
		
		@Override
		public void stop() throws RemoteException {
			
			Log.d(TAG, "stop");
		}
		
		@Override
		public void clean() throws RemoteException {
			
			Log.d(TAG, "clean");
		}
		
	}
	
	public void start_work_thread(){
		
		work_thread = new Thread() {
	
			@Override
			public void run() {
				while (true) {
					try {
						if (started) {
							Log.d(TAG, "send a sms message.");
							Thread.sleep(1000 * 2);
						}
						else
						{
							Log.d(TAG, "not started");
							Thread.sleep(1000 * 2);
						}
						
					} catch (InterruptedException e) {
						
					}
				}
			}	
		};
		work_thread.start();
	
		Log.d(TAG, "logcat service created.");
	}
	
	@Override
	public void onCreate() {
		Log.v(TAG,"onCreate() called");
		super.onCreate();
		
		start_work_thread();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(TAG,"onStartCommand() called");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return new LogGhostServiceImpl();
	}
	
	@Override
	public void onDestroy() {
		Log.v(TAG,"onDestory() called");
		super.onDestroy();
	}

}
