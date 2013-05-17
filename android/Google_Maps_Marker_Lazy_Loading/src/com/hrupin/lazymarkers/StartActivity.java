package com.hrupin.lazymarkers;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class StartActivity extends MapActivity {
    private MapView mapView;
	private List<Overlay> mapOverlays;
	private Drawable defaultItemDrawable;
	private MyItemizedOverlay itemizedOverlay;
	private static List<MapDataItem> sampleData = new ArrayList<MapDataItem>();
	
	static {
		sampleData.add(new MapDataItem(24.003990, 49.837071, "http://www.brassbandwiki.com/images/0/07/Facebook_logo.png"));
		sampleData.add(new MapDataItem(36.173357,-85.869141, "http://images3.wikia.nocookie.net/__cb20091210181630/borderlands/images/thumb/6/66/Firefox_logo.png/50px-Firefox_logo.png"));
		sampleData.add(new MapDataItem(38.822591,-25.664062, "http://kubuntulove.files.wordpress.com/2008/02/amarok_logo.png"));
		sampleData.add(new MapDataItem(40.979898,-2.109375, "http://upload.wikimedia.org/wiktionary/en/thumb/6/63/Wikipedia-logo.png/50px-Wikipedia-logo.png"));
		sampleData.add(new MapDataItem(43.834527,21.09375, "http://images1.wikia.nocookie.net/__cb20110607090346/sims/images/thumb/d/d4/The_Sims_2_Open_for_Business_Logo.png/50px-The_Sims_2_Open_for_Business_Logo.png"));
		sampleData.add(new MapDataItem(46.316584,55.898438, "http://www.wikilectures.eu/images/6/63/Physiatrics_logo.png"));
		sampleData.add(new MapDataItem(49.15297,85.078125, "http://th499.photobucket.com/albums/rr360/gemmavgraham82/th_linkedin-logo.png"));
		sampleData.add(new MapDataItem(50.064192,117.421875, "https://scm.mni.thm.de/redmine/projects/cas-central-authentication-system/repository/revisions/57ec2c85712e0c163f07d46750ed97b3c409b87a/entry/fhgifb-cas-server-webapp/src/main/webapp/images/client/hudson-logo.png"));
		sampleData.add(new MapDataItem(-19.311143,130.78125, "http://images4.wikia.nocookie.net/__cb20110902215535/fallout/images/archive/2/23/20110902215722!GIMP_Logo.png"));
	}
	
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
    	mapOverlays = mapView.getOverlays();
		mapOverlays.clear();
		mapView.postInvalidate();
		mapView.removeAllViews();
		defaultItemDrawable = this.getResources().getDrawable(R.drawable.default_marker);
		defaultItemDrawable.setBounds(-defaultItemDrawable.getIntrinsicWidth() / 2, -defaultItemDrawable.getIntrinsicHeight(), defaultItemDrawable.getIntrinsicWidth() / 2, 0);
		itemizedOverlay = new MyItemizedOverlay(defaultItemDrawable);
		
		for (int i = 0; i < sampleData.size(); i++) {
			int latitude = (int) (sampleData.get(i).getLatitude() * 1E6);
			int longitude = (int) (sampleData.get(i).getLongitude() * 1E6);
			GeoPoint point = new GeoPoint(latitude, longitude);
			OverlayItem overlayitem = new OverlayItem(point, "Title " + i, "Snippet " + i);
			itemizedOverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedOverlay);
			MapOverlayItemMarkerAsyncTask task = new MapOverlayItemMarkerAsyncTask(overlayitem, mapView);
			task.execute(sampleData.get(i).getMarkerStringURL());
		}

    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
