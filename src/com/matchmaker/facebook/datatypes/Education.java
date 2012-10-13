package com.matchmaker.facebook.datatypes;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Education extends ComplexDataType {
	
	List<Data> educationDatas;
	
	@Override
	public double matchesPersentage(ComplexDataType other) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void parseData(JSONObject json) throws JSONException {
		if (json == null)
		{
			//TODO: error handling
		}
		educationDatas = new ArrayList<Education.Data>();
		String id;
		String school;
		int year;
		String type;
		JSONArray educationPlaces = json.getJSONArray("education");
		for (int i=0; i < educationPlaces.length(); ++i)
		{
			JSONObject place = educationPlaces.getJSONObject(i);
			id = place.getJSONObject("school").getString("id");
			school = place.getJSONObject("school").getString("name");
			year = place.getJSONObject("year").getInt("name");
			type = place.getString("type");		
			educationDatas.add(new Data(id, school, year, type));
		}
	}
	
	public class Data
	{
		private String id;
		private String school;
		private int year;
		private String type;
		
		public Data(String id, String school, int year, String type)
		{
			this.id = id;
			this.school = school;
			this.year = year;
			this.type = type;
		}
		
		public String getSchool() {
			return school;
		}
		public int getYear() {
			return year;
		}
		public String getType() {
			return type;
		}
	}

}
