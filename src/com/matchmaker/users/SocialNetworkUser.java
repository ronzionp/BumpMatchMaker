package com.matchmaker.users;

public abstract class SocialNetworkUser extends User
{
	protected boolean isShareAllowed;
	protected InterestList interestList;
	
	public abstract void login();
	public abstract void logout();
	public abstract void shareApplicationWithFriends();
	public abstract void setIsShareAllowed();
	
	public boolean isShareAllowed() {
		return isShareAllowed;
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
