package com.matchmaker;

import com.bump.api.BumpAPIIntents;
import com.bump.api.IBumpAPI;
import com.matchmaker.UI.FacebookUser;
import com.matchmaker.bump.BumpBroadcastRcvr;
import com.matchmaker.bump.BumpServiceConnection;
import com.matchmaker.logger.Logger;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity {

	private IBumpAPI bumpAPI;
	private BumpBroadcastRcvr receiver;
	private BumpServiceConnection connection;

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        receiver = new BumpBroadcastRcvr(bumpAPI);
        connection = new BumpServiceConnection(bumpAPI);
        
        bindService(new Intent(IBumpAPI.class.getName()), connection, Context.BIND_AUTO_CREATE);
	    Logger.APPLICATION_BOOT.printMsgToLog();
	    IntentFilter filter = new IntentFilter();
	    filter.addAction(BumpAPIIntents.CHANNEL_CONFIRMED);
	    filter.addAction(BumpAPIIntents.DATA_RECEIVED);
	    filter.addAction(BumpAPIIntents.NOT_MATCHED);
	    filter.addAction(BumpAPIIntents.MATCHED);
	    filter.addAction(BumpAPIIntents.CONNECTED);
	    registerReceiver(receiver, filter);
	    
	    FacebookUser facebookUser = new FacebookUser(this);
	    facebookUser.login();
    }
    
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) 
//    {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
    
    @Override
    public void onDestroy() {
        Logger.APPLICATION_DESTROY.printMsgToLog();
        unbindService(connection);
        unregisterReceiver(receiver);
        super.onDestroy();
     }
}
