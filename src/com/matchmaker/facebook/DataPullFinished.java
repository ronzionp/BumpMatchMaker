package com.matchmaker.facebook;

import org.json.JSONObject;

public interface DataPullFinished {
	
	public void onSuccess(JSONObject userBasicData, JSONObject interestsList);
	public void onFailure(Exception e);

}
