package com.playercore.remoteserviceclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.playercore.remoteservicedemo.ILogGhostService;
import com.playercore.log_remoteserviceclient.R;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivty";
	
	private Button bindBtn, unbindBtn;
	private Button pb_start = null;
	private Button pb_stop = null;
	private Button pb_reset = null;
	private Button pb_clean = null;
	private String __TAG__	  = null;
	
	private ILogGhostService logcatService = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        __TAG__ = "log_ghost";
        
        bind_UI();
       
    }
    
    private void bind_UI(){
    	
    	bindBtn = (Button)findViewById(R.id.bindButton);        
        unbindBtn = (Button)findViewById(R.id.unbindButton);
        
    	
    	pb_start = ( Button ) findViewById( R.id.button_start);
    	pb_stop =  ( Button ) findViewById( R.id.button_stop);
    	pb_reset =  ( Button ) findViewById( R.id.button_reset);
    	pb_clean =  ( Button ) findViewById( R.id.button_clean);
    	
    	bindBtn.setOnClickListener(listener);
    	unbindBtn.setOnClickListener(listener);
    	pb_start.setOnClickListener(listener);
    	pb_stop.setOnClickListener(listener);
    	pb_reset.setOnClickListener(listener);
    	pb_clean.setOnClickListener(listener);
    	
    }
    
    private OnClickListener listener = new OnClickListener(){  
  	  
        @Override  
        public void onClick(View view) { 
        	switch(view.getId())
        	{
	        	case R.id.button_start:
	        	{
	        		OnStart();
	        		Log.d(__TAG__,"start");
	        	}
	        	break;
	        	case R.id.button_stop:
	        	{
	        		OnStop();
	        		Log.d(__TAG__,"stop");
	        	}
	        	break;
	        	case R.id.button_reset:
	        	{
	        		OnReset();
	        		Log.d(__TAG__,"reset");
	        	}
	        	break;
	        	case R.id.button_clean:
	        	{
	        		OnClean();
	        		Log.d(__TAG__,"clean");
	        	}
	        	case R.id.bindButton:
	        	{
	        		try{
	        			OnBind();	        			
	        		}catch(RemoteException e){
	            		Log.e(TAG, e.getMessage());
	            	}
	        	}
	        	break;
	        	case R.id.unbindButton:
	        	{
	        		try{
	        			OnUnBind();	        			
	        		}catch(RemoteException e){
	            		Log.e(TAG, e.getMessage());
	            	}
	        	}
	        	break;
        	} 
        }  
    };  
    
    private void OnBind() throws RemoteException {
    	bindService(new Intent(ILogGhostService.class.getName()), serConn, Context.BIND_AUTO_CREATE);
	}
    
    private void OnUnBind() throws RemoteException {
    	unbindService(serConn);
	}
    
    
    private void OnStart() {
    	try{
    		logcatService.start();
    	}catch(RemoteException e){
    		Log.e(TAG, e.getMessage());
    	}
	}
    
    private void OnStop() {
    	try{
    		logcatService.stop();
    	}catch(RemoteException e){
    		Log.e(TAG, e.getMessage());
    	}
	}
    
    private void OnReset() {
    	try{
    		logcatService.stop();
    		logcatService.start();
    	}catch(RemoteException e){
    		Log.e(TAG, e.getMessage());
    	}
	}
    
    private void OnClean() {
    	try{
    		logcatService.clean();    		
    	}catch(RemoteException e){
    		Log.e(TAG, e.getMessage());
    	}
	}


	private ServiceConnection serConn = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.v(TAG, "onServiceConnected() callled");
			logcatService = ILogGhostService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.v(TAG, "onServiceDisconnected() called");
			logcatService = null;
		}
    	
    };
}