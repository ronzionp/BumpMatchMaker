package com.matchmaker.bump;

import com.bump.api.IBumpAPI;
import com.matchmaker.logger.Logger;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class BumpServiceConnection implements ServiceConnection{
	
	private IBumpAPI api;
	private static final String API_KEY = "7a61a726983b4d7bb4f37cef6caba301"; //TODO: this is only development api key.
	private static final String BUMP_USER = "";
	
	public BumpServiceConnection(IBumpAPI api)
	{
		this.api = api;
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		Logger.SERVICE_CONNECTED.printMsgToLog();
        api = IBumpAPI.Stub.asInterface(binder);
        try 
        {
            api.configure(API_KEY, BUMP_USER);
        } 
        catch (RemoteException e) 
        {
            Logger.SERVICE_CONN_FAILED.printMsgToLog(Log.getStackTraceString(e));
        }		
	}

	@Override
	public void onServiceDisconnected(ComponentName name) 
	{
		 Logger.SERVICE_DISCONNECTED.printMsgToLog();
	}

}
