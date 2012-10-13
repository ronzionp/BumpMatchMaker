package com.matchmaker.facebook;

import java.util.ArrayList;
import com.matchmaker.facebook.datatypes.DataType;
import com.matchmaker.facebook.datatypes.Education;
import com.matchmaker.facebook.datatypes.Groups;
import com.matchmaker.facebook.datatypes.Hometown;
import com.matchmaker.facebook.datatypes.Interests;
import com.matchmaker.facebook.datatypes.SimpleDataType;
import com.matchmaker.facebook.datatypes.UserLikes;

public enum DataReferences {

	ID(null, "me", new SimpleDataType("id")),		
	FIRST_NAME(null, "me", new SimpleDataType("first_name")),		
	LAST_NAME(null, "me", new SimpleDataType("last_name")),		
	EMAIL("email", "me", new SimpleDataType("email")),		
	BIRTHDAY("user_birthday", "me", new SimpleDataType("birthday")),		
	HOMETOWN("user_hometown", "me", new Hometown()),
	EDUCATION("user_education_history", "me", new Education()),		
	INTERESTS("user_interests", "me/interests", new Interests()),
	BOOKS("user_likes", "me/books", new UserLikes()),	
	GAMES("user_likes", "me/games", new UserLikes()),
	PAGES("user_likes", "me/likes", new UserLikes()),
	MOVIES("user_likes", "me/movies", new UserLikes()),
	MUSIC("user_likes", "me/music", new UserLikes()),
	TV("user_likes", "me/television", new UserLikes()),
	GROUPS("user_groups", "me/groups", new Groups());
	
	private String permission;
	private String request;
	private DataType dataType;
	
	private DataReferences(String permission, String request, DataType dataType)
	{
		this.permission = permission;
		this.request = request;
		this.dataType = dataType;
	}

	public String getPermission() {
		return permission;
	}

	public String getRequest() {
		return request;
	}
	
	public DataType getDataType() {
		return dataType;
	}

	public static String[] getAllPermissions()
	{
		ArrayList<String> res = new ArrayList<String>();
		for (DataReferences value : DataReferences.values())
		{
			if (value.permission != null && !res.contains(value.permission))
				res.add(value.permission);
		}
		
		return res.toArray(new String[res.size()]);
	}

}
