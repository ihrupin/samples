package com.hrupin.sample.mappinremoving;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends MapActivity implements OnClickListener {
    private static final String TAG = StartActivity.class.getSimpleName();
    private MapView mapView;
    private List<Overlay> mapOverlays;
    private Drawable defaultItemDrawable;
    private MyItemizedOverlay itemizedOverlay;
    private Button buttonRemovePin;
    private static List<MapDataItem> sampleData = new ArrayList<MapDataItem>();

    static {
        sampleData.add(new MapDataItem(24.003990, 49.837071));
        sampleData.add(new MapDataItem(36.173357, -85.869141));
        sampleData.add(new MapDataItem(38.822591, -25.664062));
        sampleData.add(new MapDataItem(40.979898, -2.109375));
        sampleData.add(new MapDataItem(43.834527, 21.09375));
        sampleData.add(new MapDataItem(46.316584, 55.898438));
        sampleData.add(new MapDataItem(49.15297, 85.078125));
        sampleData.add(new MapDataItem(50.064192, 117.421875));
        sampleData.add(new MapDataItem(-19.311143, 130.78125));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);

        buttonRemovePin = (Button) findViewById(R.id.buttonRemoveMapPin);
        buttonRemovePin.setOnClickListener(this);

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
        }
        mapOverlays.add(itemizedOverlay);

    }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onClick(View v) {
        List<Overlay> overlays = mapView.getOverlays();
        Log.i(TAG, "Overlays count = " + overlays.size());
        MyItemizedOverlay overlay = (MyItemizedOverlay) overlays.get(0);
        Log.i(TAG, "Item count = " + overlay.size());
        if (overlay.size() > 0) {
            int itemPositionWithMaxLatitude = 0;
            for (int i = 0; i < overlay.size(); i++) {
                if(overlay.getItem(itemPositionWithMaxLatitude).getPoint().getLatitudeE6() < overlay.getItem(i).getPoint().getLatitudeE6()){
                    itemPositionWithMaxLatitude = i;
                }
            }
            overlay.remove(itemPositionWithMaxLatitude);
            mapView.invalidate();
        }

    }
}
