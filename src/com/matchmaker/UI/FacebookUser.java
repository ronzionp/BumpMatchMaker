package com.matchmaker.UI;

import android.app.Activity;
import android.content.Context;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.matchmaker.facebook.InterestsHandler;
import com.matchmaker.facebook.LoginHandler;
import com.matchmaker.facebook.PostHandler;

public class FacebookUser extends SocialNetworkUser 
{
	// Facebook Application ID must be set before running
    // See http://www.facebook.com/developers/createapp.php
	private static final String APP_ID = "";
	
	private Facebook facebook;
	private AsyncFacebookRunner asyncFacebookRunner;
	private LoginHandler loginHandler;
	private PostHandler postHandler;
	private InterestsHandler interestsHandler;
	private InterestList interestList;
	private Activity activity;
	
	public FacebookUser(Activity activity)
	{
		this.activity = activity;
		facebook = new Facebook(APP_ID);
		loginHandler = new LoginHandler(activity, facebook,  new String[]{});
		postHandler = new PostHandler(facebook, asyncFacebookRunner);
	}

	@Override
	public void shareApplicationWithFriends()
	{
		String message = ""; //TODO: create message to post on wall
		postHandler.postToWall(activity.getApplicationContext(), message);
	}

	@Override
	public void createInterestList()
	{
		interestList = interestsHandler.createInterestsList();
	}

	@Override
	public void login() 
	{
		loginHandler.login();
	}

	@Override
	public void logout()
	{
		loginHandler.logout();
	}
}
