package com.geomarket.asynctask;

import java.util.ArrayList;
import com.cloudinary.*;

import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.geomarket.adapter.OfferAdapter;
import com.geomarket.entity.Advertisement;
import com.geomarket.tab_fragment.FragmentOffer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.ListView;

public class GetAdvertList extends AsyncTask<Object, Object, Object>{
	private ProgressDialog dialog;
	private Activity activity;
	private ListView list;
	private Firebase ref;
	private Cloudinary cloudinary;
	private ArrayList<Advertisement> adverList;
	private OfferAdapter adapter;
	private String locID; 
	public GetAdvertList(Activity activity, ListView list, OfferAdapter adapter,  String locID){
		this.activity = activity;
		this.list = list;
		this.adapter = adapter;
		this.locID = locID;
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
			
		ref.addValueEventListener(new ValueEventListener(){

			@Override
			public void onCancelled(FirebaseError firebaseError) {
				// TODO Auto-generated method stub
				System.out.println("The read failed: " + firebaseError.getMessage());
			}

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				// TODO Auto-generated method stub

				Map<String, Object> advertsMap = (Map<String, Object>) snapshot.getValue();
				
				for(String i : advertsMap.keySet()){
					String url = cloudinary.url().format("jpg").transformation(new Transformation().width(1400).crop("fit")).generate(i);
					if(url == null){
						url = cloudinary.url().format("jpg").transformation(new Transformation().width(1400).crop("fit")).generate("http://res.cloudinary.com/dfm9692pu/image/upload/c_scale,w_1400/v1420164779/noImage.png");
					}

					Advertisement advert = new Advertisement();
					advert.setAdvertID(i);
					Map<String, Object> advertMap = (Map<String, Object>) advertsMap.get(i);
					
					String locResult;
					int checkSpace = advertMap.get("location").toString().indexOf(" ");
					
			
					if(checkSpace != -1){
						locResult = advertMap.get("location").toString().substring(0,advertMap.get("location").toString().indexOf(" "));
					}else{
						locResult = advertMap.get("location").toString().substring(0,advertMap.get("location").toString().length());
					}
					
					if(locID != null){
						if(locID.contains(locResult)){
							advert.setTitle(advertMap.get("title").toString());
							advert.setSubtitle(advertMap.get("subtitle").toString());
							Double salePrice = Double.parseDouble(advertMap.get("sales_price").toString());
							advert.setPrice(salePrice);
							advert.setLocation(advertMap.get("location").toString());
							Double originalPrice = Double.parseDouble(advertMap.get("original_price").toString());
							advert.setoPrice(originalPrice);
							advert.setImgUrl(url);
						
						adapter.add(advert);
						
						}
					}
						
				}
				adapter.notifyDataSetChanged();
				
			}
			
		});
		dialog.dismiss();
	}

	@Override
	protected void onPreExecute() {
		adapter.clear();
		dialog = ProgressDialog.show(activity,
				"Retrieving advertisements", "Please wait...", true);
		
		ref = new Firebase("https://geomarketnyp.firebaseio.com/advertisement");
		cloudinary = new Cloudinary(Cloudinary.asMap(
				"cloud_name","dfm9692pu",
				"api_key", "443893967666533",
				"api_secret", "uYlUVpAZK405EHc6CsrHF64VVlg"));
		
	}
	

}
