package com.geomarket.asynctask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.geomarket.entity.Advertisement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GetAdvert extends AsyncTask<Object, Object, Object>{
	private ProgressDialog dialog;
	private Activity activity;
	private Firebase ref;
	private Cloudinary cloudinary;
	private Query queryRef;
	private String itemID;
	private ImageView imgIV;
	private TextView salesPriceTV , descTV;
	public GetAdvert(Activity activity, String itemID, TextView descTV , TextView salePriceTV, ImageView imgIV){
		this.activity = activity;
		this.itemID = itemID;
		this.descTV = descTV;
		this.salesPriceTV = salesPriceTV;
		this.imgIV = imgIV;
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
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				// TODO Auto-generated method stub
				Map<String, Object> advertsMap = (Map<String, Object>) snapshot.getValue();
				String url = null;
				for(String i : advertsMap.keySet()){
					if(i.equals(itemID)){
						url = cloudinary.url().format("jpg").transformation(new Transformation().width(1400).crop("fit")).generate(i);
						loadImageFromURL(url,imgIV );
						dialog.dismiss();
					}
				}
			}
			
		});
	}

	@Override
	protected void onPreExecute() {
		dialog = ProgressDialog.show(activity,
				"Retrieving advertisements", "Please wait...", true);
		
		ref = new Firebase("https://geomarket.firebaseio.com/advertisements");
		
		cloudinary = new Cloudinary(Cloudinary.asMap(
				"cloud_name","geomarket",
				"api_key", "255469583551513",
				"api_secret", "eHi0O2T7iEtSBasnIjBgcEqV6fY"));
	}
	
	public boolean loadImageFromURL(String fileUrl, ImageView iv){
		  try {
		 
		    URL myFileUrl = new URL (fileUrl);
		    HttpURLConnection conn =
		      (HttpURLConnection) myFileUrl.openConnection();
		    conn.setDoInput(true);
		    conn.connect();
		 
		    InputStream is = (InputStream) conn.getInputStream();
		    iv.setImageBitmap(BitmapFactory.decodeStream(is));
		 
		    return true;
		 
		  } catch (MalformedURLException e) {
		    e.printStackTrace();
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		 
		  return false;
		}
}
