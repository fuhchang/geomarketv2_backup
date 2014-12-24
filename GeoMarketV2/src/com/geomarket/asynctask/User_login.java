package com.geomarket.asynctask;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.geomarket.ui_logic.MainActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class User_login extends AsyncTask<Object, Object, Object> {
	private ProgressDialog dialog;
	private Activity activity;
	private String email, password;
	private Firebase ref;
	
	public User_login(Activity activity, String email, String password){
		this.activity = activity;
		this.email = email;
		this.password = password;
		
	}
	@Override
	protected Object doInBackground(Object... arg0) {
		// TODO Auto-generated method stu
	
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
			
			@Override
			public void onAuthenticationError(FirebaseError arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(activity, arg0.toString(), Toast.LENGTH_LONG).show();
				
			}
			
			@Override
			public void onAuthenticated(AuthData arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(activity, MainActivity.class);
				activity.startActivity(intent);
			}
		});
	}

	@Override
	protected void onPreExecute() {
		Firebase.setAndroidContext(activity);
		ref = new Firebase("https://geomarket.firebaseio.com/");
		dialog = ProgressDialog.show(activity, "logging in."," Please wait....", true);
	}

	
}
