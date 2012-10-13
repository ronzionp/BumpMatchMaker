package com.matchmaker.users;

public abstract class SocialNetworkUser extends User
{
	protected boolean isShareAllowed;
	
	public abstract void login();
	public abstract void logout();
	public abstract void shareApplicationWithFriends();
	protected abstract void setIsShareAllowed();
	
	private boolean isShareAllowed() {
		return isShareAllowed;
	}	
	
}
