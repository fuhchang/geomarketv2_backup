package com.geomarket.ui_logic;

import java.util.Locale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;
import com.webileapps.navdrawer.R;

public class PlanetFragment extends SherlockFragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";

	public PlanetFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_planet, container,
				false);
		int i = getArguments().getInt(ARG_PLANET_NUMBER);
		String mainmenu = getResources().getStringArray(R.array.mainmenu)[i];

		
		return rootView;
	}
}