package com.hrupin.lazymarkers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

public class MapOverlayItemMarkerAsyncTask extends AsyncTask<String, Void, Bitmap> {

	private OverlayItem overlayitem;
	private MapView mapView;

	public MapOverlayItemMarkerAsyncTask(OverlayItem overlayitem, MapView mapView) {
		this.overlayitem = overlayitem;	
		this.mapView = mapView;
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {		
		Bitmap bitmap = null;
		InputStream in = null;
		try {
			in = fetch(params[0]);
			bitmap = BitmapFactory.decodeStream(in, null, null);
			in.close();
			return bitmap;
		} catch (IOException e1) {
			return null;
		} 
	}

	private static InputStream fetch(String address) throws MalformedURLException,IOException {
	    HttpGet httpRequest = new HttpGet(URI.create(address) );
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);
	    HttpEntity entity = response.getEntity();
	    BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
	    InputStream instream = bufHttpEntity.getContent();
	    return instream;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if (result != null) {
			Drawable drawable = new BitmapDrawable(result);
			drawable.setBounds(-drawable.getIntrinsicWidth() / 2, -drawable.getIntrinsicHeight(), drawable.getIntrinsicWidth() / 2, 0);
			overlayitem.setMarker(drawable);
			mapView.invalidate();
		}
	}
	
}
