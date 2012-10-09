package com.matchmaker.logger;

import android.util.Log;

public enum Logger {

	//**********************************************	Bump	**********************************************//
	APPLICATION_BOOT		(LogTags.APPLICATION, "Boot", Log.DEBUG), 
	APPLICATION_DESTROY		(LogTags.APPLICATION, "Destroyed", Log.DEBUG), 
	SERVICE_CONNECTED		(LogTags.CONNECTION, "Service connected", Log.DEBUG), 
	SERVICE_DISCONNECTED	(LogTags.CONNECTION, "Service disconnected", Log.DEBUG), 
	SERVICE_CONN_FAILED		(LogTags.CONNECTION, "Service connection failed\n{0}", Log.ERROR),
	DATA_RECEIVED			(LogTags.BROADCAST, "Received data from: {0}\nData: {1}", Log.DEBUG), 
	BUMP_MATCHED			(LogTags.BROADCAST, "Bump matched with: {0}", Log.DEBUG), 
	BUMP_CONFIRM_SENT		(LogTags.BROADCAST, "Bump confirmation sent", Log.DEBUG), 
	CHANNEL_CONFIRMED		(LogTags.BROADCAST, "Channel confirmed with: {0}", Log.DEBUG), 
	BUMP_NOT_MATCHED		(LogTags.BROADCAST, "Bump not matched", Log.DEBUG), 
	CONNECTED_TO_BUMP		(LogTags.BROADCAST, "Connected to Bump...", Log.DEBUG), 
	BROADCAST_RCVR_ERROR 	(LogTags.BROADCAST, "Error while trying to receive action: {0}", Log.ERROR), 
	
	//*********************************************		Facebook	********************************************//
	
	
	
	
	;
	
    
	private final static int OFF = -1;
	private String tag;
	private String message;
	private int logType;
	
	private Logger(String tag, String message, int logType)
	{
		this.tag = tag;
		this.message = message;
		this.logType = logType;
	}
	
	public void printMsgToLog()
	{
		if (logType != OFF)
			Log.println(logType, tag, message);
	}
	
	public void printMsgToLog(Object... extra)
	{
		message = String.format(message, extra);
		if (logType != OFF)
			Log.println(logType, tag, message);		
	}

}
