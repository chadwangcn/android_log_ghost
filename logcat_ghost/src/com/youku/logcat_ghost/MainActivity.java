package com.youku.logcat_ghost;

import com.youku.logcatservice.*;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	
	private Button pb_start = null;
	private Button pb_stop = null;
	private Button pb_reset = null;
	private Button pb_clean = null;
	private String __TAG__	  = null;
	private Intent server_intent = null;
	
	private interface_service  logcatService;

	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			logcatService = (interface_service) service;
			if (logcatService.isStarted()) {
				Log.d(__TAG__, "starting service...");
			} else {
				Log.d(__TAG__, "starting service...");
			}		
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			logcatService = null;
		}
	};
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        __TAG__ = "log_ghost";
        
        server_intent = new Intent("com.youku.logcatservice.LogcatService");
        startService( server_intent );  
        this.bindService(server_intent,
				this.serviceConnection, BIND_AUTO_CREATE);

        bind_UI();
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }
    
    @Override
	protected void onDestroy() {
		super.onDestroy();		
		this.unbindService(serviceConnection);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void bind_UI(){
    	
    	pb_start = ( Button ) findViewById( R.id.button_start);
    	pb_stop =  ( Button ) findViewById( R.id.button_stop);
    	pb_reset =  ( Button ) findViewById( R.id.button_reset);
    	pb_clean =  ( Button ) findViewById( R.id.button_clean);
    	
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
        		logcatService.start();
        		Log.d(__TAG__,"start");
        	}
        	break;
        	case R.id.button_stop:
        	{
        		logcatService.stop();
        		Log.d(__TAG__,"stop");
        	}
        	break;
        	case R.id.button_reset:
        	{
        		logcatService.stop();
        		logcatService.start();
        		Log.d(__TAG__,"reset");
        	}
        	break;
        	case R.id.button_clean:
        	{
        		Log.d(__TAG__,"clean");
        	}
        	break;
        	} 
        }  
    };  
      

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
           

            return rootView;
        }
    }

}
