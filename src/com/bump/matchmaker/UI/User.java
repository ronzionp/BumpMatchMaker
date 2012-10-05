package com.bump.matchmaker.UI;

import java.sql.Date;

public abstract class User 
{
	protected String firstName;
	protected String lastName;
	protected Date birthDate;
	protected boolean isShareAllowed;
	
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
	
	public abstract void shareApplicationWithFriends() throws Exception;
}
