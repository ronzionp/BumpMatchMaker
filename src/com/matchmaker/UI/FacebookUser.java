package com.matchmaker.UI;

import android.content.Context;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;

public class FacebookUser extends SocialNetworkUser 
{
	// Facebook Application ID must be set before running
    // See http://www.facebook.com/developers/createapp.php
	private static final String APP_ID = "";
	private Facebook facebook;
	private AsyncFacebookRunner asyncFacebookRunner;
	
	public FacebookUser()
	{
		facebook = new Facebook(APP_ID);
		asyncFacebookRunner = new AsyncFacebookRunner(facebook);
	}

	@Override
	public void shareApplicationWithFriends() throws Exception 
	{
		// TODO share application on user's facebook wall
	}

	@Override
	public void createInterestList() throws Exception {
		// TODO create interests from facebook api
		
	}

	@Override
	public void login(Context context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout(Context context) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
