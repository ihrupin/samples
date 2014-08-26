package com.hrupin.samples.customballoongooglemapsv2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
		OnInfoWindowClickListener, OnMarkerClickListener {

	private static final String TAG = MapsActivity.class.getSimpleName();
	private UiSettings mUiSettings;
	protected GoogleMap map;
	private SupportMapFragment mMapFragment;

	public void showMapItem() {
		LatLng position = new LatLng(37.802722, -122.417049);
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(position);
		markerOptions.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.ic_launcher));
		markerOptions.title("Sample title");
		markerOptions.snippet("Sample snippet");
		Marker posMarker = map.addMarker(markerOptions);
		posMarker.showInfoWindow();
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_map);

		mMapFragment = new SupportMapFragment() {
			@Override
			public void onActivityCreated(Bundle savedInstanceState) {
				super.onActivityCreated(savedInstanceState);
				map = mMapFragment.getMap();
				if (map != null) {
					mUiSettings = map.getUiSettings();
					mUiSettings.setMyLocationButtonEnabled(false);
					map.setMyLocationEnabled(false);
					map.setOnMarkerClickListener(MapsActivity.this);
					map.setInfoWindowAdapter(new BalloonAdapter(
							MapsActivity.this.getLayoutInflater()));
					map.setOnInfoWindowClickListener(MapsActivity.this);
				}
			}
		};
		openMap(mMapFragment);
	}

	public void openMap(SupportMapFragment mapFragment) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		fragmentTransaction
				.add(R.id.linearLayoutMapContainer, mapFragment, TAG);
		fragmentTransaction.commitAllowingStateLoss();
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		Toast.makeText(this, "onInfoWindowClick", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		if (marker.getTitle() != null) {
			return false;
		}
		return true;
	}
}
