package com.bump.matchmaker.UI;

public abstract class SocialNetworkUser extends User
{
	protected boolean isShareAllowed;
	InterestList interestList;
	
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
	 * @return user's interest list whereas each interest added by real user implementation (i.e. {@link FacebookUser}) or null if list has not been initialized. 
	 */
	public InterestList getInterestList()
	{
		return interestList;
	}
	
	
}
