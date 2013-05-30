/**
 * 
 */
package com.hrupin.sample.googlemapsv2customballoons;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class BalloonAdapter implements InfoWindowAdapter {
	public static final String IS_MY_LOCATION_MARKER = "###%%%MY_LOCATION###%%%";
	LayoutInflater inflater = null;
	private TextView textViewTitle;
	private FragmentActivity activity;

	public BalloonAdapter(LayoutInflater inflater, FragmentActivity activity) {
		this.inflater = inflater;
		this.activity = activity;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		View v = inflater.inflate(R.layout.balloon, null);
		textViewTitle = (TextView) v.findViewById(R.id.terrain);
		textViewTitle.setText(marker.getTitle());
		return (v);
	}

	@Override
	public View getInfoContents(Marker marker) {
		return (null);
	}
}
