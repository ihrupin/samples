package com.hrupin.cleaner;

import java.io.File;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	private static MyApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
	
	public static MyApplication getInstance(){
		return instance;
	}
	
	public void clearApplicationData() {
		File cache = getCacheDir();
		File appDir = new File(cache.getParent());
		if(appDir.exists()){
			String[] children = appDir.list();
			for(String s : children){
				if(!s.equals("lib")){
					deleteDir(new File(appDir, s));
					Log.i("TAG", "File /data/data/APP_PACKAGE/" + s +" DELETED");
				}
			}
		}
	}
	
	public static boolean deleteDir(File dir) {
	    if (dir != null && dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    return dir.delete();
	}
}
