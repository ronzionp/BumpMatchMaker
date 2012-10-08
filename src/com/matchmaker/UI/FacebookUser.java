package com.matchmaker.UI;

import android.app.Activity;
import android.content.Context;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.matchmaker.facebook.LoginHandler;

public class FacebookUser extends SocialNetworkUser 
{
	// Facebook Application ID must be set before running
    // See http://www.facebook.com/developers/createapp.php
	private static final String APP_ID = "";
	private Facebook facebook;
	private AsyncFacebookRunner asyncFacebookRunner;
	private LoginHandler loginHandler;
	
	public FacebookUser(Activity activity)
	{
		facebook = new Facebook(APP_ID);
		asyncFacebookRunner = new AsyncFacebookRunner(facebook);
	}

	@Override
	public void shareApplicationWithFriends()
	{
		// TODO share application on user's facebook wall
	}

	@Override
	public void createInterestList()
	{
		// TODO create interests from facebook api
		
	}

	@Override
	public void login() 
	{
		try
		{
			loginHandler.login();
		}
		catch(Exception e)
		{
			//TODO: print to log
		}
		
	}

	@Override
	public void logout()
	{
		try
		{
			loginHandler.logout();
		}
		catch(Exception e)
		{
			//TODO: print to log
		}
		
	}
}
