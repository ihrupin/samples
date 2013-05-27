package com.hrupin.maproute;

import android.util.Log;

public class Loger {
	
	public static final String firstPartOfTag = "CXRATE - ";

	public static boolean isWriteLogInLogCat = true;

	public static void i(String tag, String msg) {
		if(isWriteLogInLogCat ){
			Log.i(firstPartOfTag + tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if(isWriteLogInLogCat ){
			Log.e(firstPartOfTag + tag, msg);
		}
	}

}
