package com.hrupin.lazymarkers;

import android.content.Context;
import android.util.DisplayMetrics;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class Utils {
	private static final int BALLOON_OFFSET_IN_DP = 150;
	private static final double EQUATRE_LENGTH_IN_KM = 40075.7;
	private static final double EARTH_RADIUS_IN_KM = 6356.8;
	private static DisplayMetrics metrics = null;
	private static float density;
	
	public static GeoPoint getBalloonCenterCoords(GeoPoint markerGeoPoint, MapView mapView) {
		int zoom = mapView.getZoomLevel();
		int balloonOffset = getOffsetInDegreesE6(zoom, dpToPxConverter(mapView.getContext(), BALLOON_OFFSET_IN_DP));
		int balloonCenterLatitude = markerGeoPoint.getLatitudeE6() + balloonOffset;
		return new GeoPoint(balloonCenterLatitude, markerGeoPoint.getLongitudeE6());
	}
	
	private static int getOffsetInDegreesE6(int zoom, int offsetInPx) {
		// 256 - equator length on map if zoom level = 0
		double equatreLengthOnMapInPx = (double)256 * Math.pow(2, zoom);
		double earthRadiusOnMapInPx = (equatreLengthOnMapInPx * EARTH_RADIUS_IN_KM) / EQUATRE_LENGTH_IN_KM;
		double tangensValue = (double)offsetInPx / earthRadiusOnMapInPx;
		double offsetInDegrees = Math.toDegrees(Math.atan(tangensValue));		
		return (int)(offsetInDegrees * 1E6);
	}

	private static int dpToPxConverter(Context context, int dp){
		if (metrics == null) {
			metrics  = context.getResources().getDisplayMetrics();
			density = metrics.density;
		}
		return (int) (density * dp + 0.5f);
	}
}
