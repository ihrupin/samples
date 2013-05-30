package com.hrupin.sample.googlemapsv2customballoons;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {

	private static final LatLng LOS_ANGELES = new LatLng(34.087924, -118.212891);
	private static final LatLng SAN_FRANCISCO = new LatLng(37.779399, -122.420654);
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

		if (map != null) {
			map.addMarker(new MarkerOptions().position(LOS_ANGELES).title("Los Angeles"));
			map.addMarker(new MarkerOptions().position(SAN_FRANCISCO).title("San Francisco")
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
		}
	}

}
