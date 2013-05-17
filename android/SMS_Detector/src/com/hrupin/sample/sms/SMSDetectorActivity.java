package com.hrupin.sample.sms;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SMSDetectorActivity extends Activity {
    private String smsBody;
    private TextView textView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView)findViewById(R.id.smsBody);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Bundle b = getIntent().getExtras();
        if(b!= null){
            if(!b.isEmpty()){
                smsBody = b.getString(SmsBroadcastReceiver.SMS_CONTENT);
            }
        }
        if(smsBody!= null){
            textView.setText(smsBody);
        }
    }
}