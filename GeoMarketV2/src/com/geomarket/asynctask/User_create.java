package com.geomarket.asynctask;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.geomarket.entity.User;
import com.geomarket.ui_logic.MainActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class User_create extends AsyncTask<Object, Object, Object> {
	private ProgressDialog dialog;
	private Activity activity;
	private User user;
	private Firebase ref;
	public User_create(Activity activity,User user){
		this.activity = activity;
		this.user = user;
	}
	@Override
	protected Object doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		ref.createUser(user.getEmail(), user.getPwd(), new Firebase.ResultHandler(){

			@Override
			public void onError(FirebaseError arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(activity, "Please check your input fill", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(activity, "user created", Toast.LENGTH_LONG).show();
				
				ref.authWithPassword(user.getEmail(), user.getPwd(), new Firebase.AuthResultHandler() {
					
					@Override
					public void onAuthenticationError(FirebaseError arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAuthenticated(AuthData authData) {
						// TODO Auto-generated method stub
						Firebase usersRef = ref.child("users");
						
						Map<String, String> userMap = new HashMap<String, String>();
						userMap.put("email", user.getEmail());
						userMap.put("fName", user.getFname());
						userMap.put("lName", user.getlName());
						userMap.put("address", user.getAddress());
						userMap.put("phone", user.getContact());
						userMap.put("nric", user.getNric());
						userMap.put("role", user.getRole());
						/*
						Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
						users.put(authData.getUid(), userMap);
						*/
						Map<String, Object> users = new HashMap<String, Object>();
						users.put("Userid_"+authData.getUid().substring(12), userMap);
						ref.updateChildren(users);
						dialog.dismiss();
						Intent intent = new Intent(activity, MainActivity.class);
						activity.startActivity(intent);
						activity.finish();
						
					}
				});
				
			}
			
		});
	}

	@Override
	protected void onPreExecute() {
		dialog = ProgressDialog.show(activity, "creating."," Please wait....", true);
		ref = new Firebase("https://geomarket.firebaseio.com/user");
		
	}
	

	
}
