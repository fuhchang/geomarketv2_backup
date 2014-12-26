package com.geomarket.ui_logic;

import com.webileapps.navdrawer.R;
import com.webileapps.navdrawer.R.layout;
import com.webileapps.navdrawer.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ViewOffer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_offer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_offer, menu);
		return true;
	}

}
