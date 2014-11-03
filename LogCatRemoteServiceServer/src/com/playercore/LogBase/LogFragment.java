package com.playercore.LogBase;

import android.app.Activity;
import android.app.ListFragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ShareActionProvider;

import java.util.ArrayList;
import java.util.List;

/*
public class LogFragment extends ListFragment implements LogHandler.OnLogItemReadListener, AbsListView.MultiChoiceModeListener {
    private static final String ARG_LOG_LEVEL = "loglevel";
    private LogHandler mLogHandler;
    private LogAdapter mLogAdapter;
    private Priority mLogLevel;
    private ShareActionProvider mShareActionProvider;
    private LogTextFactory mLogTextFactory;
    private LogFilter mLogFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLogTextFactory = new LogcatTextFactory();

        if(savedInstanceState != null) {
            mLogLevel = Priority.valueOf(savedInstanceState.getString(ARG_LOG_LEVEL));
        }
        else {
            mLogLevel = Priority.VERBOSE;
        }

        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ARG_LOG_LEVEL, mLogLevel.name());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Context context = getActivity();
        if(context != null) {
            context.bindService(new Intent(context, LogHandlerService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mLogHandler = ((LogHandlerService.LogHandlerServiceBinder) service).getLogHandler();
            mLogFilter = new LogPriorityFilter(mLogHandler);

            loadItems();

            mLogHandler.addOnLogItemReadListener(LogFragment.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mLogHandler.removeOnLogItemReadListener(LogFragment.this);
            mLogHandler = null;
        }
    };

    private void loadItems() {
        if(mLogAdapter == null) {
            mLogAdapter = new LogAdapter(getActivity(), mLogFilter.getFilteredItems(mLogLevel));
            
        }
        else {
            mLogAdapter.setItems(mLogFilter.getFilteredItems(mLogLevel));
            mLogAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        Context context = getActivity();
        if(context != null) {
            context.unbindService(mServiceConnection);
        }

        super.onDestroy();
    }

    @Override
    public void onLogItemRead(LogItem item) {
        if(isAdded()) {
            loadItems();
        }
    }

}*/
