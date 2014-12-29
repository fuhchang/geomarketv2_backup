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

import java.util.ArrayList;
import java.util.Map;

import java.util.List;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.geomarket.geofencing.GeofenceRequester;
import com.geomarket.geofencing.SimpleGeofence;
import com.geomarket.geofencing.SimpleGeofenceStore;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.model.LatLng;
import com.webileapps.navdrawer.R;

public class MainActivity extends SherlockFragmentActivity {
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mMainMenu;
	
	//geofence varable

	private double myLat;
	private double myLng;
	private Location location;
	private boolean gps_enabled = false;
	private boolean network_enabled = false;
	private LatLng current_location;
	private SimpleGeofenceStore mPrefs;
	private Location mCurrentLocation;
	private int radius = 500;
	private GeofenceRequester mGeofenceRequester;
	private SimpleGeofence UiGeofence;
	private Firebase ref;
	List<Geofence> mCurrentGeofences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ref.setAndroidContext(getApplicationContext());
		ref = new Firebase("https://geomarket.firebaseio.com/location");
		mTitle = mDrawerTitle = getTitle();
		mMainMenu = getResources().getStringArray(R.array.mainmenu);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item,  mMainMenu));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
		//getMyCurrentLocation();
		
		mCurrentGeofences = new ArrayList<Geofence>();
		mPrefs = new SimpleGeofenceStore(MainActivity.this);
		mGeofenceRequester = new GeofenceRequester(MainActivity.this);
	
		
		ref.addValueEventListener(new ValueEventListener(){

			@Override
			public void onCancelled(FirebaseError error) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				// TODO Auto-generated method stub

				Map<String, Object> locMaps = (Map<String, Object>) snapshot.getValue();

				for(String i : locMaps.keySet()){
					Map<String, Object> locMap = (Map<String, Object>) locMaps.get(i);
					
					UiGeofence = new SimpleGeofence(i + " has some offer faster find them!!", Double.parseDouble(locMap.get("lat").toString()), Double.parseDouble(locMap.get("lng").toString()), radius,Geofence.NEVER_EXPIRE, Geofence.GEOFENCE_TRANSITION_ENTER);
					mPrefs.setGeofence("There is some offer Near you find them now!!!", UiGeofence);
					mCurrentGeofences.add(UiGeofence.toGeofence());
				}
				
				
				try{
					mGeofenceRequester.addGeofences(mCurrentGeofences, "HELLO", "there is a offer near you!! test", 0);
					
				}catch(UnsupportedOperationException e){

					Toast.makeText(getApplicationContext(), R.string.add_geofences_already_requested_error, Toast.LENGTH_LONG).show();
				}
			}
			
		});

		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {

		if(item.getItemId() == android.R.id.home){
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}
		

		else if(item.getItemId() == R.id.action_contact){
			// QuickContactFragment dialog = new QuickContactFragment();
			// dialog.show(getSupportFragmentManager(), "QuickContactFragment");
			// return true;
		}

		return super.onOptionsItemSelected(item);
	}
		

	// The click listener for ListView in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}
	

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private void selectItem(int position) {
		SherlockFragment fragment= null;
		switch (position) {
		case 0:
			getSupportFragmentManager().beginTransaction().replace(R.id.content,PageSlidingTabStripFragment.newInstance()).commit();
			break;
		case 1:
			fragment = new Fragment_Profile();
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.content, fragment).commit();
			break;
		default:
			break;
		}
		setTitle(mMainMenu[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	public void setTitle(CharSequence title){
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

}