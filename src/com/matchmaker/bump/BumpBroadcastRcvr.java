package com.matchmaker.bump;

import com.bump.api.BumpAPIIntents;
import com.bump.api.IBumpAPI;
import com.matchmaker.logger.Logger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

public class BumpBroadcastRcvr extends BroadcastReceiver{
	
	private IBumpAPI api;
	
	public BumpBroadcastRcvr(IBumpAPI api)
	{
		this.api = api;
	}

	@Override
	public void onReceive(Context context, Intent intent) 
	{
		final String action = intent.getAction();
        try 
        {
            if (action.equals(BumpAPIIntents.DATA_RECEIVED)) 
            {
                Logger.DATA_RECEIVED.printMsgToLog(api.userIDForChannelID(intent.getLongExtra("channelID", 0)), 
                		new String(intent.getByteArrayExtra("data")));
                
            } 
            else if (action.equals(BumpAPIIntents.MATCHED)) 
            {
                long channelID = intent.getLongExtra("proposedChannelID", 0); 
                Logger.BUMP_MATCHED.printMsgToLog(api.userIDForChannelID(channelID));
                api.confirm(channelID, true);
                Logger.BUMP_CONFIRM_SENT.printMsgToLog();
            } 
            else if (action.equals(BumpAPIIntents.CHANNEL_CONFIRMED)) 
            {
                long channelID = intent.getLongExtra("channelID", 0);
                Logger.CHANNEL_CONFIRMED.printMsgToLog(api.userIDForChannelID(channelID));
                sendDataThroughChannel(channelID);
            } 
            else if (action.equals(BumpAPIIntents.NOT_MATCHED)) 
            {
                Logger.BUMP_NOT_MATCHED.printMsgToLog();
            } 
            else if (action.equals(BumpAPIIntents.CONNECTED)) 
            {
            	Logger.CONNECTED_TO_BUMP.printMsgToLog();
                api.enableBumping();
            }
        } 
        catch (RemoteException e) 
        {
        	Logger.BROADCAST_RCVR_ERROR.printMsgToLog(e.getMessage());
        }
		
	}

	private void sendDataThroughChannel(long channelID) throws RemoteException 
	{
		//TODO: implement data to broadcast through channel
		 api.send(channelID, "Data as String".getBytes());		
	}

}
