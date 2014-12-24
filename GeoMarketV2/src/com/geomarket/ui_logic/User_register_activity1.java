package com.geomarket.ui_logic;

import java.util.ArrayList;


import com.beardedhen.androidbootstrap.BootstrapButton;
import com.geomarket.validation.Form;
import com.geomarket.validation.Validate;
import com.geomarket.validation.validator.NotEmptyValidator;
import com.geomarket.validation_controller.registervalidation;
import com.webileapps.navdrawer.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class User_register_activity1 extends Activity {
	private EditText  passwordET,cfmpwdET,emailET;
	private BootstrapButton btnNext; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_register_activity1);
		RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.rLayout_register);
		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.bg1);
		rLayout.setBackgroundDrawable(drawable);
		

		emailET= (EditText) findViewById(R.id.emailET);
		emailET.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		emailET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		emailET.setTextSize(15);
		
		passwordET = (EditText) findViewById(R.id.passwordET);
		passwordET.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		
		cfmpwdET = (EditText) findViewById(R.id.cfmpwdET);
		cfmpwdET.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		
		emailET= (EditText) findViewById(R.id.emailET);
		emailET.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		btnNext = (BootstrapButton) findViewById(R.id.nextBtn);
		btnNext.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view ) {
				// TODO Auto-generated method stub
				Form mForm = new Form();
				if(passwordET.getText().toString().equals(cfmpwdET.getText().toString())){
					Validate validEmail = new Validate(emailET);
					Validate validPWD = new Validate(passwordET);
					Validate validCFM = new Validate(cfmpwdET);
					validEmail.addValidator(new NotEmptyValidator(User_register_activity1.this));
					validPWD.addValidator(new NotEmptyValidator(User_register_activity1.this));
					validCFM.addValidator(new NotEmptyValidator(User_register_activity1.this));
					
					mForm.addValidates(validEmail);
					mForm.addValidates(validPWD);
					mForm.addValidates(validCFM);
					
					ArrayList<Validate> validList = new ArrayList<Validate>();
					validList.add(validEmail);
					validList.add(validPWD);
					validList.add(validCFM);
					
					Intent intent = new Intent();
					registervalidation registerController = new registervalidation(User_register_activity1.this);
					registerController.validateForm(intent, mForm, validList);
				}else{
					Toast.makeText(getApplicationContext(), "Password and confirm password has to be the same", Toast.LENGTH_LONG).show();
				}
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_register_activity1, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		return super.onOptionsItemSelected(item);
	}

	public EditText getPasswordET() {
		return passwordET;
	}

	public void setPasswordET(EditText passwordET) {
		this.passwordET = passwordET;
	}

	public EditText getCfmpwdET() {
		return cfmpwdET;
	}

	public void setCfmpwdET(EditText cfmpwdET) {
		this.cfmpwdET = cfmpwdET;
	}

	public EditText getEmailET() {
		return emailET;
	}

	public void setEmailET(EditText emailET) {
		this.emailET = emailET;
	}
	
	
}
