package com.matchmaker.facebook.datatypes;

import org.json.JSONException;
import org.json.JSONObject;

public class Hometown extends ComplexDataType {
	
	String id;

	@Override
	public double matchesPersentage(ComplexDataType cdt) {
		if (!(cdt instanceof Hometown))
			return 0;
		
		Hometown other = (Hometown)cdt;
		return this.id.equals(other.getID()) ? 1 : 0;
	}

	@Override
	public void parseData(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		
	}
	
	public String getID()
	{
		return this.id;
	}

}
