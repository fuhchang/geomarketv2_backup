package com.geomarket.tab_fragment;

import java.util.ArrayList;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.geomarket.adapter.OfferAdapter;
import com.geomarket.asynctask.GetAdvertList;
import com.geomarket.entity.Advertisement;
import com.geomarket.ui_logic.ViewOffer;
import com.google.android.gms.location.LocationListener;
import com.webileapps.navdrawer.R;

import com.webileapps.navdrawer.R.layout;
import com.webileapps.navdrawer.R.menu;

import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FragmentOffer extends Fragment {
	private ListView listview;
	private OfferAdapter adapter;
	private String locID; 
	private ArrayList<Advertisement> advertList;
	private SharedPreferences.OnSharedPreferenceChangeListener prefListener;
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		loadSavedPreferences();
		
		View rootView = inflater.inflate(R.layout.activity_fragment_offer, container, false);
		rootView.setBackgroundColor(Color.LTGRAY);
		listview = (ListView) rootView.findViewById(R.id.offerList);
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
		
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
		prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sp,
					String key) {
				// TODO Auto-generated method stub
				System.out.println("testingkey " +key);
				if(key.equals("geoID")){
					locID = sp.getString("geoID", "location name");
					Log.d("key value", locID);
					Toast.makeText(getActivity(), sp.getString("geoID", "location name"), Toast.LENGTH_LONG).show();
					GetAdvertList getAdvert = new GetAdvertList(getActivity(), listview ,adapter, locID);
					getAdvert.execute();
				}else{
					Toast.makeText(getActivity(), "doing nth", Toast.LENGTH_LONG).show();
				}
			}
		};
		sp.registerOnSharedPreferenceChangeListener(prefListener);
		
		GetAdvertList getAdvert = new GetAdvertList(getActivity(), listview ,adapter, locID);
		getAdvert.execute();
		return rootView;
	}

	
	private void loadSavedPreferences(){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
		locID = sp.getString("geoID", "location name");
		System.out.println("loc id "+locID);
	}
	
	

}
