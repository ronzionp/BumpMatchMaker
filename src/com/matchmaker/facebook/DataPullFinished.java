package com.matchmaker.facebook;

import org.json.JSONObject;

public interface DataPullFinished {
	
	public void onSuccess(
			JSONObject userBasicData,
			JSONObject interests,
			JSONObject books,
			JSONObject games,
			JSONObject pages,
			JSONObject movies,
			JSONObject music,
			JSONObject tv,
			JSONObject groups);
	
	public void onFailure(Exception e);

}
