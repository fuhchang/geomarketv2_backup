package com.geomarket.ui_logic;

import java.util.ArrayList;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.geomarket.validation.Form;
import com.geomarket.validation.Validate;
import com.geomarket.validation.validator.NotEmptyValidator;
import com.geomarket.validation_controller.registervalidation2;
import com.webileapps.navdrawer.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class User_register_activity2 extends Activity {
	private String  password, email;
	private EditText fName, Lname, nric, phone, address;
	private BootstrapButton btnCreate;
	private Firebase ref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_register_activity2);
		Firebase.setAndroidContext(this);
		ref = new Firebase("https://geomarket.firebaseio.com/");
		
		RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.rLayout_register2);
		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.bg1);
		rLayout.setBackgroundDrawable(drawable);
		password = getIntent().getStringExtra("pwd");
		email = getIntent().getStringExtra("email");
		setEmail(email);
		setPassword(password);
		fName = (EditText) findViewById(R.id.fNameET);
		fName.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		Lname = (EditText) findViewById(R.id.lNameET);
		Lname.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		nric = (EditText) findViewById(R.id.nricET);
		nric.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		phone = (EditText) findViewById(R.id.phoneET);
		phone.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		address = (EditText) findViewById(R.id.addressET);
		address.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		btnCreate = (BootstrapButton) findViewById(R.id.btnRegister);
		btnCreate.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Form mForm = new Form();
				Validate validFname= new Validate(fName);
				Validate validLname= new Validate(Lname);
				Validate validnric= new Validate(nric);
				Validate validphone= new Validate(phone);
				Validate validaddress= new Validate(address);
				
				validFname.addValidator(new NotEmptyValidator(User_register_activity2.this));
				validLname.addValidator(new NotEmptyValidator(User_register_activity2.this));
				validnric.addValidator(new NotEmptyValidator(User_register_activity2.this));
				validphone.addValidator(new NotEmptyValidator(User_register_activity2.this));
				validaddress.addValidator(new NotEmptyValidator(User_register_activity2.this));
				
				mForm.addValidates(validFname);
				mForm.addValidates(validLname);
				mForm.addValidates(validnric);
				mForm.addValidates(validphone);
				mForm.addValidates(validaddress);
				
				ArrayList<Validate> validList = new ArrayList<Validate>();
				validList.add(validFname);
				validList.add(validLname);
				validList.add(validnric);
				validList.add(validphone);
				validList.add(validaddress);
				
				Intent intent = new Intent();
				registervalidation2 registerController = new registervalidation2(User_register_activity2.this);
				
				registerController.validateForm(intent, mForm, validList);
				
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_register_activity2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		return super.onOptionsItemSelected(item);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EditText getfName() {
		return fName;
	}

	public void setfName(EditText fName) {
		this.fName = fName;
	}

	public EditText getLname() {
		return Lname;
	}

	public void setLname(EditText lname) {
		Lname = lname;
	}

	public EditText getNric() {
		return nric;
	}

	public void setNric(EditText nric) {
		this.nric = nric;
	}

	public EditText getPhone() {
		return phone;
	}

	public void setPhone(EditText phone) {
		this.phone = phone;
	}

	public EditText getAddress() {
		return address;
	}

	public void setAddress(EditText address) {
		this.address = address;
	}
	
}
