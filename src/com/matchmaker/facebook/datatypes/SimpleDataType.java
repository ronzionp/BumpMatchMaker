package com.matchmaker.facebook.datatypes;

import org.json.JSONException;
import org.json.JSONObject;

public class SimpleDataType extends DataType {
	
	protected String value;
	
	public SimpleDataType(String value)
	{
		this.value = value;
	}

	public String getValue(JSONObject json) throws JSONException {
		if (json.has(value))
			return json.getString(value);
		else
			return null;
	}
}
