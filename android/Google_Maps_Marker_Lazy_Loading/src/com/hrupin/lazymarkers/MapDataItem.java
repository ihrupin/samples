package com.hrupin.lazymarkers;


public class MapDataItem {
	private double latitude;
	private double longitude;
	private String url;
	
	public MapDataItem(double latitude, double longitude, String url) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.url = url;
	}

	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public String getMarkerStringURL(){
		return url;
	}
}
