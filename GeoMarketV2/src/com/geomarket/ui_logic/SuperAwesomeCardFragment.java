/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.geomarket.ui_logic;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.geomarket.tab_fragment.FragmentOffer;
import com.geomarket.tab_fragment.Fragmentgooglemap;
import com.google.android.gms.maps.MapView;
import com.webileapps.navdrawer.FragmentFav;
import com.webileapps.navdrawer.R;


public class SuperAwesomeCardFragment extends SherlockFragment{

	private static final String ARG_POSITION = "position";

	private int position;

	public static SuperAwesomeCardFragment newInstance(int position) {
		SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		FrameLayout fl = new FrameLayout(getActivity());
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		fl.setLayoutParams(params);
		
		System.out.println("my position "+position);
		
		switch(position){
		
		case 0:
			View offerView = inflater.inflate(R.layout.activity_fragment_offer, null);
			fl.addView(offerView);
			Fragment offerFragment = new FragmentOffer();
			getFragmentManager().beginTransaction().replace(R.id.tab_offerview, offerFragment).commit();
			break;
		case 1:
			View favView = inflater.inflate(R.layout.activity_fragment_fav, null);
			fl.addView(favView);
			Fragment favFragment = new FragmentFav();
			getFragmentManager().beginTransaction().replace(R.id.tab_favview, favFragment).commit();
			break;
		case 2:
			View GoogleMapView = inflater.inflate(R.layout.activity_fragmentgooglemap, null);
			fl.addView(GoogleMapView);
			Fragment googlefragment = new Fragmentgooglemap();
			getFragmentManager().beginTransaction().replace(R.id.tab_googlemap, googlefragment).commit();
			break;
		}
		
		return fl;
	}
	
	
}