package com.hrupin.maproute;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.hrupin.maproute.route.Road;
import com.hrupin.maproute.route.RoadProvider;
import com.hrupin.maproute.route.RouteOverlay;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class ShowRouteAsyncTask extends AsyncTask<Object, Object, Boolean> {

	private static final String TAG = ShowRouteAsyncTask.class.getSimpleName();
	private Road mRoad;
	private MapView mapView;
	private ProgressDialog dialog;

	public ShowRouteAsyncTask(MapView mapView) {
		this.mapView = mapView;
	}

	@Override
	protected Boolean doInBackground(Object... params) {
		double fromLat = 49.85, fromLon = 24.016667, toLat = 50.45, toLon = 30.523333;
		String url = RoadProvider.getUrl(fromLat, fromLon, toLat, toLon);
		Loger.i(TAG, "ROUTE URL:" + url);
		InputStream is = getConnection(url);
		mRoad = RoadProvider.getRoute(is);
		return true;
	}

	@Override
	protected void onPostExecute(Boolean isSuccess) {
		if(dialog.isShowing()){
			dialog.dismiss();
		}
		RouteOverlay mapOverlay = new RouteOverlay(mRoad, mapView);
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.clear();
		listOfOverlays.add(mapOverlay);
		mapView.invalidate();
	}
	
	@Override
	protected void onPreExecute() {
		dialog = new ProgressDialog(mapView.getContext());
		dialog.setMessage("Wait while route builds");
		if(!dialog.isShowing()){
			dialog.show();
		}
	}
	
	private InputStream getConnection(String url) {
		InputStream is = null;
		try {
			URLConnection conn = new URL(url).openConnection();
			is = conn.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
}
