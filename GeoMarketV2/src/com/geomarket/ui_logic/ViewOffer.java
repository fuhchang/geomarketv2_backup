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
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewOffer extends Activity {
	private LinearLayout ll;
	private RelativeLayout rl1, rl2, rlIM;
	private TextView descTV, salesPriceTV, generalTV,generalTitleTV, discountTV;
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
		ll =  (LinearLayout) findViewById(R.id.rlviewoffer);
		ll.setBackgroundColor(Color.LTGRAY);
		rl1 = (RelativeLayout) findViewById(R.id.rl1);
		rl1.setBackgroundColor(Color.WHITE);
		rl2 = (RelativeLayout) findViewById(R.id.rl2);
		rl2.setBackgroundColor(Color.WHITE);
		rlIM = (RelativeLayout) findViewById(R.id.rlIM);
		rlIM.setBackgroundColor(Color.WHITE);
		descTV = (TextView) findViewById(R.id.descTV);
		descTV.setTextColor(Color.BLACK);
		salesPriceTV = (TextView) findViewById(R.id.salesPriceTV);
		salesPriceTV.setTextColor(Color.BLACK);
		salesPriceTV.setTextSize(20);
		
		generalTV = (TextView) findViewById(R.id.generalTV);
		generalTV.setTextColor(Color.BLACK);
		generalTitleTV = (TextView) findViewById(R.id.generalTitleTV);
		generalTitleTV.setTextColor(Color.BLACK);
		generalTitleTV.setTextSize(20);
		discountTV = (TextView) findViewById(R.id.discountTV);
		Bundle data = getIntent().getExtras();
		Advertisement advert = data.getParcelable("advert");
		GetAdvert getadvert = new GetAdvert(this,advert.getAdvertID(), descTV, salesPriceTV, imgIV, generalTV, discountTV);
		getadvert.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_offer, menu);
		return true;
	}

}
