package com.hrupin.sample.mappinremoving;


public class MapDataItem {
	private double latitude;
	private double longitude;
	
	public MapDataItem(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
}
