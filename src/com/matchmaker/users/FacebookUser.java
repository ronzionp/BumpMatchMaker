package com.matchmaker.users;

import java.text.SimpleDateFormat;

import org.json.JSONObject;

import android.app.Activity;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.matchmaker.facebook.DataHandler;
import com.matchmaker.facebook.DataPullFinished;
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
    private SimpleDateFormat dateFormatter;
    
    
	private AuthListener authListener = new AuthListener() {
		
		@Override
		public void onAuthSucceed() {
			dataHandler.start();
		}

		@Override
		public void onAuthFail(String error) {
			// TODO Auto-generated method stub
			
		}
	};
	
    private DataPullFinished dataListener = new DataPullFinished() {
		
		@Override
		public void onSuccess(JSONObject userBasicData, JSONObject interestsList) {
			if (userBasicData == null || interestsList == null)
				//TODO: error handling
				
			//Data is not null
			parseBasicUserData(userBasicData);
			parseUserInterestsList(interestsList);
		}
		
		@Override
		public void onFailure(Exception e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	public FacebookUser(Activity activity)
	{
		this.activity = activity;
		facebook = new Facebook(APP_ID);
		loginHandler = new LoginHandler(activity, facebook,  new String[]{"user_interests", "email", "user_birthday"});
		postHandler = new PostHandler(facebook, asyncFacebookRunner);
		dateFormatter = new SimpleDateFormat("mm/DD/yyyy");
		dataHandler = new DataHandler(facebook);
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
		dataHandler.addDataPullFinishedListener(dataListener);
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
	
	
	private void parseBasicUserData(JSONObject userBasicData) 
	{
		try
		{
			if (userBasicData.has("id"))
				setFacebookID(userBasicData.getString("id"));
			if (userBasicData.has("email"))
				setEmail(userBasicData.getString("email"));
			if (userBasicData.has("last_name"))
				setLastName(userBasicData.getString("last_name"));
			if (userBasicData.has("first_name"))
				setFirstName(userBasicData.getString("first_name"));
			if (userBasicData.has("birthday"))
				setBirthDay(dateFormatter.parse(userBasicData.getString("birthday")));	
		}
		catch (Exception e)
		{
			//TODO: error handling
		}
	}
	

	public void parseUserInterestsList(JSONObject interestsList)
	{
		try
		{
			//TODO: parse interests from response
			
		}
		catch(Exception e)
		{
			//TODO: error handling
		}			
	}

	@Override
	public void setIsShareAllowed() {
		// TODO Auto-generated method stub
		
	}
	
}
