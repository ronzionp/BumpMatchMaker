package com.matchmaker.facebook;

import java.text.SimpleDateFormat;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.BaseRequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Util;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.matchmaker.UI.FacebookUser;
import com.matchmaker.facebook.SessionEvents.AuthListener;
import com.matchmaker.facebook.SessionEvents.LogoutListener;

public class LoginHandler {
	
	private Facebook facebook;
    private Handler handler;
    private SessionListener sessionListener = new SessionListener();
    private String[] permissions;
    private Activity activity;

    
    public LoginHandler(Activity activity, Facebook facebook, String[] permissions)
    {
    	this.facebook = facebook;
    	this.activity = activity;
    	this.permissions = permissions;
    	handler = new Handler();
        SessionEvents.addAuthListener(sessionListener);
        SessionEvents.addLogoutListener(sessionListener);
    }
    
    public void login()
    {   
    	if (!facebook.isSessionValid())  
        {
            facebook.authorize(activity, permissions, new LoginDialogListener());
        }
    }
    
    public void logout()
    {
    	if (facebook.isSessionValid()) 
        {
            SessionEvents.onLogoutBegin();
            AsyncFacebookRunner asyncRunner = new AsyncFacebookRunner(facebook);
            asyncRunner.logout(activity.getApplicationContext(), new LogoutRequestListener());
        } 
    }
    
    private final class LoginDialogListener implements DialogListener {
        public void onComplete(Bundle values) {
            SessionEvents.onLoginSuccess();            
        }

        public void onFacebookError(FacebookError error) {
            SessionEvents.onLoginError(error.getMessage());
        }
        
        public void onError(DialogError error) {
            SessionEvents.onLoginError(error.getMessage());
        }

        public void onCancel() {
            SessionEvents.onLoginError("Action Canceled");
        }
    }
    
    private class LogoutRequestListener extends BaseRequestListener {
        public void onComplete(String response, final Object state) 
        {
            handler.post(new Runnable() {
                public void run() {
                    SessionEvents.onLogoutFinish();
                }
            });
        }
    }
    
    private class SessionListener implements AuthListener, LogoutListener {
        
        public void onAuthSucceed() 
        {
            SessionStore.save(facebook, activity.getApplicationContext());
        }

        public void onAuthFail(String error) 
        {
        	//TODO: print to log
        }
        
        public void onLogoutBegin() 
        {
        	//TODO: print to log
        }
        
        public void onLogoutFinish() 
        {
            SessionStore.clear(activity.getApplicationContext());
        }
    }

}
