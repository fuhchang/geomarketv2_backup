package com.geomarket.tab_fragment;

import java.util.ArrayList;

import com.geomarket.adapter.OfferAdapter;
import com.geomarket.asynctask.GetAdvertList;
import com.geomarket.entity.Advertisement;
import com.geomarket.ui_logic.ViewOffer;
import com.webileapps.navdrawer.R;
import com.webileapps.navdrawer.R.layout;
import com.webileapps.navdrawer.R.menu;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentSelectedOffer extends Fragment  {
	private ListView listview;
	private OfferAdapter adapter;
	private String locID; 
	private RelativeLayout rl;
	private ArrayList<Advertisement> advertList;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		loadSavedPreferences();
		View rootView = inflater.inflate(R.layout.activity_fragment_selected_offer, container, false);
		rootView.setBackgroundColor(Color.TRANSPARENT);
		rl = (RelativeLayout) rootView.findViewById(R.id.tab_selectedOffer);
		listview = (ListView) rootView.findViewById(R.id.selofferList);
		advertList = new ArrayList<Advertisement>();
		adapter = new OfferAdapter(this.getActivity(), advertList);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(getActivity(), ViewOffer.class);
				Advertisement advert = new  Advertisement();
				advert.setAdvertID(adapter.getItem(position).getAdvertID());

				intent.putExtra("advert", advert);
				startActivity(intent);
			}
			
		});
		
		GetAdvertList getAdvert = new GetAdvertList(this.getActivity(), listview ,adapter , locID);
		getAdvert.execute();
		
		
		return rootView;
	}
	
	
	private void loadSavedPreferences(){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
		locID = sp.getString("selgeoID", "location name");
		System.out.println("loc id "+locID);
	}
	
	
	
}
