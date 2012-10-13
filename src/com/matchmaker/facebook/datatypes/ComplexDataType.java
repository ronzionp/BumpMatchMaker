package com.matchmaker.facebook.datatypes;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ComplexDataType extends DataType {

	public abstract double matchesPersentage(ComplexDataType other);
	public abstract void parseData(JSONObject json) throws JSONException;

}
