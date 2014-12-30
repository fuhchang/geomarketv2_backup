package com.geomarket.tab_fragment;

import com.webileapps.navdrawer.R;
import com.webileapps.navdrawer.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class FragmentFav extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.activity_fragment_fav, container,false);
		rootView.setBackgroundColor(Color.LTGRAY);
		
		return rootView;
	}

	

}
