package com.hrupin.samples.androidgeofencessample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hrupin.samples.androidgeofencessample.App;

/**
 * Created by Igor Khrupin www.hrupin.com on 2/21/17.
 */

public class GeofenceDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "geofences.db";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + GeofenceContract.GeofenceEntry.TABLE_NAME + " (" +
                    GeofenceContract.GeofenceEntry._ID + " INTEGER PRIMARY KEY," +
                    GeofenceContract.GeofenceEntry.COLUMN_NAME_KEY + " TEXT," +
                    GeofenceContract.GeofenceEntry.COLUMN_NAME_LAT + " TEXT," +
                    GeofenceContract.GeofenceEntry.COLUMN_NAME_LNG + " TEXT," +
                    GeofenceContract.GeofenceEntry.COLUMN_NAME_EXPIRES + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + GeofenceContract.GeofenceEntry.TABLE_NAME;


    public GeofenceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static GeofenceDbHelper get() {
        return new GeofenceDbHelper(App.getInstance());
    }
}