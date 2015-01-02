package com.geomarket.tab_fragment;

import java.util.ArrayList;

import java.util.Map;

import java.util.List;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.geomarket.geofencing.GeofenceRequester;
import com.geomarket.geofencing.SimpleGeofence;
import com.geomarket.geofencing.SimpleGeofenceStore;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MarkerOptionsCreator;
import com.webileapps.navdrawer.R;
import com.webileapps.navdrawer.R.id;
import com.webileapps.navdrawer.R.layout;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;

public class Fragmentgooglemap extends Fragment  implements LocationListener,OnMarkerClickListener, OnMarkerDragListener, GooglePlayServicesClient.ConnectionCallbacks{
	private MapView mMap;
	private GoogleMap gMap;
	private double lat = 1.3792457;
	private double Lng = 103.85;
	private Firebase ref = new Firebase("https://geomarketnyp.firebaseio.com/location");
	private int radius =100;
	

	public Fragmentgooglemap(){
		
	}
	@Override
	public View onCreateView(final LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View rootView = inflater.inflate(R.layout.activity_fragmentgooglemap, container, false);

		MapsInitializer.initialize(this.getActivity());
		mMap = (MapView) rootView.findViewById(R.id.googleMapView);
		mMap.onCreate(savedInstanceState);
		
		gMap = mMap.getMap();
		gMap.getUiSettings().setMyLocationButtonEnabled(false);
		gMap.setMyLocationEnabled(true);
		
		ref.addValueEventListener(new ValueEventListener(){

			@Override
			public void onCancelled(FirebaseError firebaseError) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "The read Fail " + firebaseError.getMessage() , Toast.LENGTH_LONG).show();
			}

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				// TODO Auto-generated method stub
				Map<String, Object> locsMap = (Map<String, Object>) snapshot.getValue();
				for(String i : locsMap.keySet()){
					Map<String, Object> locMap = (Map<String, Object>) locsMap.get(i);
					
					gMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(locMap.get("lat").toString()), Double.parseDouble(locMap.get("lng").toString()))).title(i).snippet("Click here to view the offer!!")).showInfoWindow();
					gMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener(){

						@Override
						public void onInfoWindowClick(Marker marker) {
							// TODO Auto-generated method stub
							System.out.println(marker.getTitle());
							savePreferences("selgeoID", marker.getTitle());
							Fragment selOfferFragment = new FragmentSelectedOffer();
							Fragmentgooglemap.this.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.tab_googlemap, selOfferFragment, "selOffer").commit();
							
						}
						
					});
					CircleOptions co = new CircleOptions().center(new LatLng(Double.parseDouble(locMap.get("lat").toString()), Double.parseDouble(locMap.get("lng").toString()))).radius(radius).fillColor(0x40ff0000).strokeColor(Color.TRANSPARENT).strokeWidth(2);
					Circle circle = gMap.addCircle(co);
				}
			}
			
		});
		
		
		
		
		
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, Lng), 13);
		gMap.animateCamera(cameraUpdate);
		
		return rootView;
	}
	
	

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mMap.onDestroy();
	}
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		mMap.onLowMemory();
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mMap.onPause();
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mMap.onResume();
	}
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMarkerDragEnd(Marker arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private void savePreferences(String key, String value){
    	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
    	Editor edit = sp.edit();
    	edit.putString(key, value);
    	edit.commit();
    }

}
