package com.matchmaker.facebook;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import com.facebook.android.Facebook;
import com.facebook.android.Util;

public class DataHandler extends Thread
{
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
		JSONObject books = null;
		JSONObject games = null;
		JSONObject pages = null;
		JSONObject movies = null;
		JSONObject music = null;
		JSONObject tv = null;
		JSONObject groups = null;
		try
		{
			basicUserData = Util.parseJson(facebook.request(DataReferences.ID.getRequest()));
			interestsList = Util.parseJson(facebook.request(DataReferences.INTERESTS.getRequest()));
			books = Util.parseJson(facebook.request(DataReferences.BOOKS.getRequest()));
			games = Util.parseJson(facebook.request(DataReferences.GAMES.getRequest()));
			pages = Util.parseJson(facebook.request(DataReferences.PAGES.getRequest()));
			movies = Util.parseJson(facebook.request(DataReferences.MOVIES.getRequest()));
			music = Util.parseJson(facebook.request(DataReferences.MUSIC.getRequest()));
			tv = Util.parseJson(facebook.request(DataReferences.TV.getRequest()));
			groups = Util.parseJson(facebook.request(DataReferences.GROUPS.getRequest()));
			
			for (DataPullFinished listener : listeners)
			{
				listener.onSuccess(basicUserData, interestsList, books, games, pages, movies, music, tv, groups);
			}
		}
		catch (Exception e)
		{
			for (DataPullFinished listener : listeners)
			{
				listener.onFailure(e);
			}
		}
	}

}
