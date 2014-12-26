package com.geomarket.ui_logic;

import com.cloudinary.Cloudinary;

import com.firebase.client.Firebase;
import com.geomarket.asynctask.GetAdvert;
import com.geomarket.entity.Advertisement;
import com.webileapps.navdrawer.R;
import com.webileapps.navdrawer.R.layout;
import com.webileapps.navdrawer.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewOffer extends Activity {
	private RelativeLayout rl;
	private TextView descTV, salesPriceTV;
	private Firebase ref;
	private Cloudinary cloudinary;
	private String url = "https://geomarket.firebaseio.com";
	private ImageView imgIV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_offer);
		ref.setAndroidContext(this);
		ref = new Firebase(url);
		imgIV = (ImageView) findViewById(R.id.imgIV);
		rl = (RelativeLayout) findViewById(R.id.rlviewoffer);
		rl.setBackgroundColor(Color.WHITE);
		descTV = (TextView) findViewById(R.id.descTV);
		descTV.setTextColor(Color.BLACK);
		salesPriceTV = (TextView) findViewById(R.id.salesPriceTV);
		salesPriceTV.setTextColor(Color.BLACK);
		Bundle data = getIntent().getExtras();
		Advertisement advert = data.getParcelable("advert");
		GetAdvert getadvert = new GetAdvert(this,advert.getAdvertID(), descTV, salesPriceTV, imgIV);
		getadvert.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_offer, menu);
		return true;
	}

}
