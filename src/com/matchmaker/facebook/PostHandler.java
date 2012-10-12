package com.matchmaker.facebook;
import android.content.Context;
import android.os.Bundle;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.BaseDialogListener;
import com.facebook.android.BaseRequestListener;
import com.facebook.android.Facebook;

public class PostHandler {
	
	private Facebook facebook;
	private AsyncFacebookRunner asyncFacebookRunner;
	
	public PostHandler(Facebook facebook, AsyncFacebookRunner asyncFacebookRunner)
	{
		this.facebook = facebook;
		this.asyncFacebookRunner = asyncFacebookRunner;
	}
	
	public void postToWall(Context context, String message)
	{
		facebook.dialog(context, "feed", new SampleDialogListener());
	}
	
	private class SampleDialogListener extends BaseDialogListener {

        public void onComplete(Bundle values) {
            final String postId = values.getString("post_id");
            if (postId != null) 
            {
            	//TODO: print to log - Dialog Success! post_id=postId
            	asyncFacebookRunner.request(postId, new WallPostRequestListener());
            } 
            else 
            {
            	//TODO: print to log - No wall post made
            }
        }
    }
	
	 private class WallPostRequestListener extends BaseRequestListener {

	        public void onComplete(final String response, final Object state) 
	        {
	            //Log.d("Facebook-Example", "Got response: " + response);           
	        }
	    }

}
