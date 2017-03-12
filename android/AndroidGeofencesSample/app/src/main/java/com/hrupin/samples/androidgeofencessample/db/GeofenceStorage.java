package com.hrupin.samples.androidgeofencessample.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Igor Khrupin www.hrupin.com on 2/21/17.
 */

public class GeofenceStorage {
    private static final String TAG = "GeofenceStorage";

    public static void saveToDb(String key, LatLng latLng, long expires) {
        GeofenceDbHelper helper = GeofenceDbHelper.get();

        try {
            ContentValues values = new ContentValues();
            values.put(GeofenceContract.GeofenceEntry.COLUMN_NAME_KEY, key);
            values.put(GeofenceContract.GeofenceEntry.COLUMN_NAME_EXPIRES, expires + "");
            values.put(GeofenceContract.GeofenceEntry.COLUMN_NAME_LAT, latLng.latitude + "");
            values.put(GeofenceContract.GeofenceEntry.COLUMN_NAME_LNG, latLng.longitude + "");

            long id = helper.getWritableDatabase().insert(GeofenceContract.GeofenceEntry.TABLE_NAME, null, values);

            Log.i(TAG, "Row inserted id=" + id + ", ContentValues=" + values);
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "Unable save geofence to db");
        }
    }

    public static Cursor getCursor(){
        String[] columns = new String[]{GeofenceContract.GeofenceEntry._ID, GeofenceContract.GeofenceEntry.COLUMN_NAME_KEY, GeofenceContract.GeofenceEntry.COLUMN_NAME_LNG, GeofenceContract.GeofenceEntry.COLUMN_NAME_LAT, GeofenceContract.GeofenceEntry.COLUMN_NAME_EXPIRES};
        Cursor cursor = GeofenceDbHelper.get().getReadableDatabase().query(GeofenceContract.GeofenceEntry.TABLE_NAME, columns, null, null, null, null, GeofenceContract.GeofenceEntry._ID + " DESC");
        return cursor;
    }

    public static void removeGeofence(String requestId) {
        String where = GeofenceContract.GeofenceEntry.COLUMN_NAME_KEY + " = '" + requestId + "'";
        int result = GeofenceDbHelper.get().getReadableDatabase().delete(GeofenceContract.GeofenceEntry.TABLE_NAME, where, null);
        Log.i(TAG, "removeGeofence requestId=" + requestId + ", number of deleted rows=" + result);
    }
}
