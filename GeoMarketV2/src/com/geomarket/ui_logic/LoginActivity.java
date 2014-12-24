package com.geomarket.ui_logic;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.firebase.client.Firebase;
import com.geomarket.asynctask.User_login;
import com.webileapps.navdrawer.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;


public class LoginActivity extends Activity {
	private EditText emailET, passwordET;
	private BootstrapButton btnlogin, btnregister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.rLayout);
		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.bg1);
		rLayout.setBackgroundDrawable(drawable);
		emailET = (EditText) findViewById(R.id.emailET);
		passwordET = (EditText) findViewById(R.id.passwordET);
		emailET.setTextSize(20);
		emailET.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		passwordET.setTextSize(20);
		passwordET.setTextColor(getResources().getColor(R.color.abs__background_holo_dark));
		emailET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		emailET.setText("haha@gmail.com");
		passwordET.setText("123123");
		btnregister = (BootstrapButton) findViewById(R.id.registerBtn);
		btnlogin = (BootstrapButton) findViewById(R.id.loginBtn);

		btnregister.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent registerIntent = new Intent(LoginActivity.this, User_register_activity1.class);
				startActivity(registerIntent);
			}
			
		});
		
		btnlogin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				User_login login = new User_login(LoginActivity.this,emailET.getText().toString(), passwordET.getText().toString());
				login.execute();
					
				
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	
}

