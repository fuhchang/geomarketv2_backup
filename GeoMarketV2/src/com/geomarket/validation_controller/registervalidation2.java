package com.geomarket.validation_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import android.content.Intent;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.geomarket.asynctask.User_create;
import com.geomarket.crouton.Crouton;
import com.geomarket.crouton.Style;
import com.geomarket.entity.User;
import com.geomarket.ui_logic.MainActivity;
import com.geomarket.ui_logic.User_register_activity2;
import com.geomarket.validation.Form;
import com.geomarket.validation.Validate;

public class registervalidation2 {
	private User_register_activity2 activity;
	private Firebase ref;
	public registervalidation2(User_register_activity2 activity){
		this.activity =activity;
		ref = new Firebase("https://geomarket.firebaseio.com/user");
	}
	
	public void validateForm(Intent intent , Form mForm,ArrayList<Validate> validatorsArrList ){
		
		if(mForm.validate()){
			
			User user = new User();
			user.setEmail(activity.getEmail());
			user.setPwd(activity.getPassword());
			user.setAddress(activity.getAddress().getText().toString());
			user.setContact(activity.getPhone().getText().toString());
			user.setNric(activity.getNric().getText().toString());
			user.setFname(activity.getfName().getText().toString());
			user.setlName(activity.getLname().getText().toString());
			user.setRole("user");
			User_create create = new User_create(activity, user); 
			create.execute();
		}else{
			if(!validatorsArrList.get(0).isValid()){
				Crouton crouton = Crouton.makeText(activity,"Please fill in the details.",Style.ALERT);
				crouton.show();
			}
		}
	}
}
