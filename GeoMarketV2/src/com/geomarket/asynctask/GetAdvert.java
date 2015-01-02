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
	private TextView salesPriceTV , descTV, general, discountTV;
	public GetAdvert(Activity activity, String itemID, TextView descTV , TextView salesPriceTV, ImageView imgIV, TextView general, TextView discountTV){
		this.activity = activity;
		this.itemID = itemID;
		this.descTV = descTV;
		this.salesPriceTV = salesPriceTV;
		this.imgIV = imgIV;
		this.general = general;
		this.discountTV = discountTV;
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
						url = cloudinary.url().format("jpg").transformation(new Transformation().width(1600).crop("fit")).generate(i);
						loadImageFromURL(url,imgIV );
						Map<String, Object> advertMap = (Map<String, Object>) advertsMap.get(i);
						descTV.setText(advertMap.get("description").toString());
						salesPriceTV.setText("Offer Price: $" + advertMap.get("sales_price").toString());
						float before_discount = (float) ((float) (Double.parseDouble(advertMap.get("sales_price").toString())*100)/Double.parseDouble(advertMap.get("original_price").toString()));
						int discountPrice = (int) (100 - before_discount);
						discountTV.setText("Discount: "+discountPrice + "%");
						general.setText(advertMap.get("general_info").toString());
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
		
		ref = new Firebase("https://geomarketnyp.firebaseio.com/advertisement");
		
		cloudinary = new Cloudinary(Cloudinary.asMap(
				"cloud_name","dfm9692pu",
				"api_key", "443893967666533",
				"api_secret", "uYlUVpAZK405EHc6CsrHF64VVlg"));
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
