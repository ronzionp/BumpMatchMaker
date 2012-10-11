package com.matchmaker.facebook;

import org.json.JSONObject;

import com.facebook.android.Facebook;
import com.facebook.android.Util;
import com.matchmaker.UI.FacebookUser;
import com.matchmaker.UI.InterestList;

public class DataHandler {
	
	private static final String BASIC_DATA_QUERY = "me";
	private static final String INTERESTS_LIST_QUERY = "me/interests";
	
	private JSONObject responseJson;

	public JSONObject getInterestsList(final Facebook facebook) 
	{
		return requestForData(facebook, INTERESTS_LIST_QUERY);
	}

	/**
	 * This method return the basic user data in json object format or null in case of an error.
	 * @param facebook api object to inspect.
	 * @return
	 */
	public JSONObject getBasicUserData(final Facebook facebook) 
	{
		return requestForData(facebook, BASIC_DATA_QUERY);
	}
	
	private JSONObject requestForData(final Facebook facebook, final String query)
	{
		responseJson = null;
		Thread networkRequestThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try
				{
					responseJson = Util.parseJson(facebook.request(query));
					this.notifyAll();
				}
				catch(Exception ex)
				{
					//TODO: error handling
				}			
				
			}
		});
	    networkRequestThread.start();	
	    try
	    {
	    	//TODO: log - waiting for User basic data response
	    	this.wait();
	    }
	    catch (Exception e)
	    {
	    	//TODO: error handling
	    }
	    
	    return responseJson;
	}

}
