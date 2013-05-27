package com.hrupin.maproute;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class StartActivity extends MapActivity {
    protected static final String TAG = StartActivity.class.getSimpleName();
	private MapView mapView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);        
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
		ShowRouteAsyncTask task = new ShowRouteAsyncTask(mapView);
		task.execute();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
