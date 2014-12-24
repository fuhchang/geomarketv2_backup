package com.geomarket.validation_controller;

import com.geomarket.crouton.Crouton;
import com.geomarket.crouton.Style;
import com.geomarket.ui_logic.User_register_activity1;
import com.geomarket.ui_logic.User_register_activity2;
import com.geomarket.validation.Form;
import com.geomarket.validation.Validate;
import java.util.ArrayList;
import android.content.Intent;

public class registervalidation {
	private User_register_activity1 activity;
	public registervalidation(User_register_activity1 activity){
		this.activity = activity;
	}
	
	public void validateForm(Intent intent, Form mForm, ArrayList<Validate> validatorsArrList){
		if(mForm.validate()){
			intent = new Intent(activity, User_register_activity2.class);
			intent.putExtra("email", activity.getEmailET().getText().toString());
			intent.putExtra("pwd", activity.getPasswordET().getText().toString());
			activity.startActivity(intent);
			activity.finish();
			
		}else{
			if(!validatorsArrList.get(0).isValid()){
				Crouton crouton = Crouton.makeText(activity,"Please fill in the details.",Style.ALERT);
				crouton.show();
			}
		}
	}
}
