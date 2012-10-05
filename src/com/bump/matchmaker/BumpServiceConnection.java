package com.bump.matchmaker;

import com.bump.api.IBumpAPI;
import com.bump.matchmaker.logger.Logger;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class BumpServiceConnection implements ServiceConnection{
	
	private IBumpAPI api;
	private String api_key;
	private String bump_user;
	
	public BumpServiceConnection(IBumpAPI api)
	{
		this.api = api;
		
		//TODO: initialize api key and bump user
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		Logger.SERVICE_CONNECTED.printMsgToLog();
        api = IBumpAPI.Stub.asInterface(binder);
        try 
        {
            api.configure(api_key, bump_user);
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
