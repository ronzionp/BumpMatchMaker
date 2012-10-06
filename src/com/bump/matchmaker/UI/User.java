package com.bump.matchmaker.UI;

import java.sql.Date;

public abstract class User 
{
	protected String firstName;
	protected String lastName;
	protected Date birthDate;
	protected boolean isShareAllowed;
	InterestList interestList;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}	
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
	
	public abstract void shareApplicationWithFriends() throws Exception;
	public abstract void createInterestList() throws Exception;
}
