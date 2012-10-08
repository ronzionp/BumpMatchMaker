package com.matchmaker.UI;

import android.content.Context;

public abstract class SocialNetworkUser extends User
{
	protected boolean isShareAllowed;
	protected InterestList interestList;
	
	public abstract void login(Context context) throws Exception;
	public abstract void logout(Context context) throws Exception;
	public abstract void shareApplicationWithFriends() throws Exception;
	public abstract void createInterestList() throws Exception;
	
	public boolean isShareAllowed() {
		return isShareAllowed;
	}
	public void setShareAllowed(boolean isShareAllowed) {
		this.isShareAllowed = isShareAllowed;
	}
	/**
	 * 
	 * @return user's interest list whereas each interest added by real user implementation (i.e. {@link FacebookUser}) or null if list has not been created. 
	 */
	public InterestList getInterestList()
	{
		return interestList;
	}
	
	
}
