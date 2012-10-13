package com.matchmaker.users;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import org.json.JSONObject;
import android.app.Activity;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.matchmaker.facebook.DataHandler;
import com.matchmaker.facebook.DataPullFinished;
import com.matchmaker.facebook.LoginHandler;
import com.matchmaker.facebook.DataReferences;
import com.matchmaker.facebook.PostHandler;
import com.matchmaker.facebook.SessionEvents;
import com.matchmaker.facebook.SessionEvents.AuthListener;
import com.matchmaker.facebook.datatypes.Education;
import com.matchmaker.facebook.datatypes.Groups;
import com.matchmaker.facebook.datatypes.Hometown;
import com.matchmaker.facebook.datatypes.Interests;
import com.matchmaker.facebook.datatypes.SimpleDataType;
import com.matchmaker.facebook.datatypes.UserLikes;

public class FacebookUser extends SocialNetworkUser 
{
	// Facebook Application ID must be set before running
    // See http://www.facebook.com/developers/createapp.php
	private static final String APP_ID = "446897885356287";
	
	private String facebookID;
	private Hometown hometown;
	private Education education;
	private Hashtable<DataReferences, UserLikes> userLikes;
	private Interests interests;
	private Groups groups;
	
	private Facebook facebook;
	private AsyncFacebookRunner asyncFacebookRunner;
	private LoginHandler loginHandler;
	private PostHandler postHandler;
	private DataHandler dataHandler;
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
		public void onSuccess(JSONObject userBasicData, JSONObject interests,
				JSONObject books, JSONObject games, JSONObject pages,
				JSONObject movies, JSONObject music, JSONObject tv, JSONObject groups) 
		{
			getBasicUserData(userBasicData);
			getUserInterests(interests);
			
			Hashtable<DataReferences, JSONObject> userLikesProps = new Hashtable<DataReferences, JSONObject>();
			userLikesProps.put(DataReferences.BOOKS, books);
			userLikesProps.put(DataReferences.GAMES, games);
			userLikesProps.put(DataReferences.PAGES, pages);
			userLikesProps.put(DataReferences.MOVIES, movies);
			userLikesProps.put(DataReferences.TV, tv);
			getUserLikes(userLikesProps);
			
			getUserGroups(groups);			
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
		loginHandler = new LoginHandler(activity, facebook,  DataReferences.getAllPermissions());
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
	
	@Override
	protected void setIsShareAllowed() {
		// TODO Auto-generated method stub
		
	}	
	
	private void getBasicUserData(JSONObject userBasicData) 
	{
		try
		{
			// Get Basics
			this.facebookID = ((SimpleDataType)DataReferences.ID.getDataType()).getValue(userBasicData);
			this.email = ((SimpleDataType)DataReferences.EMAIL.getDataType()).getValue(userBasicData);
			this.lastName = ((SimpleDataType)DataReferences.LAST_NAME.getDataType()).getValue(userBasicData);
			this.firstName = ((SimpleDataType)DataReferences.FIRST_NAME.getDataType()).getValue(userBasicData);
			String birthdayStr = ((SimpleDataType)DataReferences.BIRTHDAY.getDataType()).getValue(userBasicData);
			this.birthDay = dateFormatter.parse(birthdayStr);
			
			// Get Hometown and Education
			this.hometown = (Hometown)DataReferences.HOMETOWN.getDataType();
			this.hometown.parseData(userBasicData);
			this.education = (Education)DataReferences.EDUCATION.getDataType();
			this.education.parseData(userBasicData);
		}
		catch (Exception e)
		{
			//TODO: error handling
		}
	}
	
	private void getUserGroups(JSONObject groups) {
		try
		{
			this.groups = (Groups)DataReferences.GROUPS.getDataType();
			this.groups.parseData(groups);
		}
		catch (Exception e)
		{
			//TODO: error handling
		}
	}

	private void getUserLikes(Hashtable<DataReferences, JSONObject> userLikesProps) 
	{
		this.userLikes = new Hashtable<DataReferences, UserLikes>();
		for (DataReferences key : userLikesProps.keySet())
		{
			try
			{
				UserLikes value = (UserLikes)key.getDataType();
				value.parseData(userLikesProps.get(key));
				this.userLikes.put(key, value);
			}
			catch (Exception e)
			{
				//TODO: error handling
			}
		}
	}

	private void getUserInterests(JSONObject interests) {
		try
		{
			this.interests = (Interests)DataReferences.INTERESTS.getDataType();
			this.interests.parseData(interests);
		}
		catch (Exception e)
		{
			//TODO: error handling
		}
	}	
}
