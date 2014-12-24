package com.geomarket.adapter;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.geomarket.entity.Advertisement;
import com.webileapps.navdrawer.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class OfferAdapter extends ArrayAdapter<Advertisement>{
	private final Activity context;
	private ArrayList<Advertisement> advertList = new ArrayList<Advertisement>();
	private TextView titleTV, subtitleTV, salePrice, salePercentage, bCount, dis;
	private ImageView imgIV;

	public OfferAdapter(Context context, ArrayList<Advertisement> advertList) {
		super(context, R.layout.activity_offer_adapter, advertList);
		this.context = (Activity) context;
		this.advertList = advertList;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.activity_offer_adapter, null, true);
		rowView.setBackgroundColor(Color.WHITE);
		imgIV = (ImageView) rowView.findViewById(R.id.imgIV);
		
		loadImageFromURL(advertList.get(position).getImgUrl(), imgIV);

		titleTV = (TextView) rowView.findViewById(R.id.title);
		titleTV.setText(advertList.get(position).getTitle());
		titleTV.setTextSize(25);
		titleTV.setTextColor(Color.BLACK);
		subtitleTV = (TextView) rowView.findViewById(R.id.subtitle);
		subtitleTV.setText(advertList.get(position).getSubtitle());
		subtitleTV.setTextSize(15);
		subtitleTV.setTextColor(Color.DKGRAY);
		salePrice = (TextView) rowView.findViewById(R.id.salePrice);
		salePrice.setTextSize(25);
		String price = Double.toString(advertList.get(position).getPrice());
		salePrice.setText("$"+price);
		salePrice.setTextColor(Color.BLACK);
		String original_Price = Double.toString(advertList.get(position).getoPrice());
		float before_discount = (float) ((advertList.get(position).getPrice()*100)/advertList.get(position).getoPrice());
		int discount = (int) (100 - before_discount);
		salePercentage = (TextView) rowView.findViewById(R.id.salePercentageTitle);
		
		salePercentage.setTextColor(Color.BLACK);
		dis = (TextView) rowView.findViewById(R.id.salePercentage);
		dis.setTextColor(Color.BLACK);
		dis.setText(discount+ "%");
		return rowView;
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
