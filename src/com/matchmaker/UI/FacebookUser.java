package com.matchmaker.UI;

import java.text.SimpleDateFormat;

import org.json.JSONObject;

import android.app.Activity;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.matchmaker.facebook.DataHandler;
import com.matchmaker.facebook.LoginHandler;
import com.matchmaker.facebook.PostHandler;
import com.matchmaker.facebook.SessionEvents;
import com.matchmaker.facebook.SessionEvents.AuthListener;

public class FacebookUser extends SocialNetworkUser 
{
	// Facebook Application ID must be set before running
    // See http://www.facebook.com/developers/createapp.php
	private static final String APP_ID = "446897885356287";
	
	private String facebookID;
	
	private Facebook facebook;
	private AsyncFacebookRunner asyncFacebookRunner;
	private LoginHandler loginHandler;
	private PostHandler postHandler;
	private DataHandler dataHandler;
	private InterestList interestList;
	private Activity activity;
	private AuthListener authListener;
    private SimpleDateFormat dateFormatter;
	
	
	public FacebookUser(Activity activity)
	{
		this.activity = activity;
		facebook = new Facebook(APP_ID);
		loginHandler = new LoginHandler(activity, facebook,  new String[]{"user_interests", "email", "user_birthday"});
		postHandler = new PostHandler(facebook, asyncFacebookRunner);
		dateFormatter = new SimpleDateFormat("mm/DD/yyyy");
		authListener = new AuthListener() {
			
			@Override
			public void onAuthSucceed() {
				pullBasicUserData();
				createInterestList();
			}

			@Override
			public void onAuthFail(String error) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	public void shareApplicationWithFriends()
	{
		String message = ""; //TODO: create message to post on wall
		postHandler.postToWall(activity.getApplicationContext(), message);
	}
	
	@Override
	public void login() 
	{
		SessionEvents.addAuthListener(authListener);
		loginHandler.login();
	}

	@Override
	public void logout()
	{
		loginHandler.logout();
	}

	public String getFacebookID() {
		return facebookID;
	}

	public void setFacebookID(String facebookID) {
		this.facebookID = facebookID;
	}
	
	
	private void pullBasicUserData() 
	{
		try
		{
			JSONObject json = dataHandler.getBasicUserData(facebook);
			if (json == null)
			{
				//TODO: error handling;
				return;
			}
			
			// Json is not null:
			if (json.has("id"))
				setFacebookID(json.getString("id"));
			if (json.has("email"))
				setEmail(json.getString("email"));
			if (json.has("last_name"))
				setLastName(json.getString("last_name"));
			if (json.has("first_name"))
				setFirstName(json.getString("first_name"));
			if (json.has("birthday"))
				setBirthDay(dateFormatter.parse(json.getString("birthday")));	
		}
		catch(Exception e)
		{
			//TODO: error handling;
		}
	}
	

	@Override
	public void createInterestList()
	{
		try
		{
			JSONObject json = dataHandler.getInterestsList(facebook);
			if (json == null)
			{
				//TODO: error handling;
				return;
			}
			//TODO: parse interests from response
			
		}
		catch(Exception e)
		{
			//TODO: error handling;
		}			
				
		
	}
	
}
