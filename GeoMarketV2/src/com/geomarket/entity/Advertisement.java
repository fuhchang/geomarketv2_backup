package com.geomarket.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Advertisement implements Parcelable {
	private String advertID,title , subtitle, desc, imgUrl, location;
	private double price, oPrice;
	public String getAdvertID() {
		return advertID;
	}
	public void setAdvertID(String advertID) {
		this.advertID = advertID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getoPrice() {
		return oPrice;
	}
	public void setoPrice(double oPrice) {
		this.oPrice = oPrice;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(advertID);
		dest.writeString(title);
		dest.writeString(subtitle);
		dest.writeString(desc);
		dest.writeString(imgUrl);
		dest.writeString(location);
		dest.writeDouble(price);
		dest.writeDouble(oPrice);
	}
	
	public Advertisement(Parcel source){
		super();
		readFromParcel(source);
	}
	public Advertisement() {
		// TODO Auto-generated constructor stub
	}
	public static final Parcelable.Creator<Advertisement> CREATOR = new Parcelable.Creator<Advertisement>() {

		@Override
		public Advertisement createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Advertisement(source);
		}

		@Override
		public Advertisement[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Advertisement[size];
		}
	};
	
	public void readFromParcel(Parcel in){
		this.advertID = in.readString();
		this.title = in.readString();
		this.subtitle = in.readString();
		this.desc = in.readString();
		this.imgUrl = in.readString();
		this.location = in.readString();
		this.price = in.readDouble();
		this.oPrice = in.readDouble();
	}
}
