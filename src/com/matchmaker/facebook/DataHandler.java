package com.matchmaker.facebook;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.facebook.android.Facebook;
import com.facebook.android.Util;
import com.matchmaker.users.FacebookUser;
import com.matchmaker.users.InterestList;

public class DataHandler extends Thread
{
	private static final String BASIC_DATA_QUERY = "me";
	private static final String INTERESTS_LIST_QUERY = "me/interests";
	private List<DataPullFinished> listeners;
	private Facebook facebook;
	
	public DataHandler(Facebook facebook)
	{
		this.facebook = facebook;
		listeners = new ArrayList<DataPullFinished>();
	}
	
	public void addDataPullFinishedListener(DataPullFinished listener)
	{
		if (listeners == null)
			listeners  = new ArrayList<DataPullFinished>();
		
		listeners.add(listener);
	}
	
	public void removeDataPullFinishedListener(DataPullFinished listener)
	{
		if (listeners != null && listeners.contains(listener))
			listeners.remove(listener);
	}

	/**
	 * This method will start as a new Threat a facebook request for user basic data and interests list
	 * in order to use results one must register with {@link DataPullFinished} before call this method.
	 */
	@Override
	public synchronized void run() {
		super.run();
		JSONObject basicUserData = null;
		JSONObject interestsList = null;
		try
		{
			basicUserData = Util.parseJson(facebook.request((BASIC_DATA_QUERY)));
			interestsList = Util.parseJson(facebook.request((INTERESTS_LIST_QUERY)));
		}
		catch (Exception e)
		{
			for (DataPullFinished listener : listeners)
			{
				listener.onFailure(e);
			}
		}
		
		for (DataPullFinished listener : listeners)
		{
			listener.onSuccess(basicUserData, interestsList);
		}
	}

}
