package com.hrupin.sample.sms;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = SmsBroadcastReceiver.class.getSimpleName();
    public static final String SMS_CONTENT = "sms_content";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Intent recieved: " + intent.getAction());

        Cursor c = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        c.moveToFirst();
        String smsBody = c.getString(12);
        Toast.makeText(context, "SMS RECEIVED:", Toast.LENGTH_LONG).show();
        Toast.makeText(context, smsBody, Toast.LENGTH_LONG).show();
        
        Intent fireActivityIntent = new Intent(context, SMSDetectorActivity.class);
        fireActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        fireActivityIntent.putExtra(SMS_CONTENT, smsBody);
        context.startActivity(fireActivityIntent);
        

    }
}
