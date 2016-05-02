package com.hrupin.activityrecognitionwithgoogle;

import android.support.v7.app.AppCompatActivity;
import java.io.File;
import java.io.FileWriter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.ActivityRecognition;

public class MainActivity extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener,
        OnClickListener {

    private static final String version = "1";
    private PendingIntent pIntent;
    private BroadcastReceiver receiver;
    private TextView tvActivity;
    private GoogleApiClient client;
    private Button bSend;
    private Button bClear;
    private File tempFile;
    private FileWriter writer;

    private static String curAct;
    private static int curCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvActivity = (TextView) findViewById(R.id.tvActivity);
        bSend = (Button) findViewById(R.id.buttonSend);
        bSend.setOnClickListener(this);
        bClear = (Button) findViewById(R.id.buttonClear);
        bClear.setOnClickListener(this);

        int resp = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resp == ConnectionResult.SUCCESS) {
            client = new GoogleApiClient.Builder(this).addApi(ActivityRecognition.API).addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            client.connect();
        } else {
            Toast.makeText(this, "Please install Google Play Service.", Toast.LENGTH_SHORT).show();
        }

        receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String time = getTime();
                String act = intent.getStringExtra("Activity");
                int con = intent.getExtras().getInt("Confidence");
                Toast.makeText(MainActivity.this, time + ", A: " + act + ", C: " + con, Toast.LENGTH_SHORT).show();
                if(act != null){
                    if(curAct == null || (!curAct.equals(act) || curCon != con)){
                        curAct = act;
                        curCon = con;
                        String v = time + ": Activity :" + act + " " + "Confidence : "
                                + con + "\n";
                        v += tvActivity.getText();
                        tvActivity.setText(v);
                    }
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(ActivityRecognitionService.ACTION);
        registerReceiver(receiver, filter);
        Toast.makeText(this, "STARTED !!!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (client != null) {
            ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(client, pIntent);
            client.disconnect();
            clear();
            Toast.makeText(this, "STOPPED !!!", Toast.LENGTH_LONG).show();
        }
        unregisterReceiver(receiver);
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected(Bundle arg0) {
        if (client != null) {
            Intent intent = new Intent(this, ActivityRecognitionService.class);
            pIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(client, 1000, pIntent);
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSend) {
            String text = null;
            try {
                text = tvActivity.getText().toString();
            } catch (Exception e) {

            }
            if (text != null) {
                try {
                    tempFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                            "report" + version + ".txt");

                    writer = new FileWriter(tempFile);
                    writer.write(text);
                    writer.close();
                    Toast.makeText(this, "Temporarily saved contents in " + tempFile.getPath(), Toast.LENGTH_SHORT)
                            .show();
                } catch (Exception e) {

                }

                tempFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        "report" + version + ".txt");

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(android.content.Intent.EXTRA_SUBJECT, "[com.hrupin.activityrecognitionwithgoogle] version " + version);

                email.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + tempFile.getAbsoluteFile()));
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Send Report"));
                clear();
            }
        } else if (v.getId() == R.id.buttonClear) {
            clear();
        }

    }

    private void clear() {
        try {
            tvActivity.setText(getTime() + ":: cleared");
        } catch (Exception e) {

        }
        curAct = null;
        curCon = 0;
    }

    private String getTime() {
        Time t = new Time();
        t.setToNow();
        return t.format2445();
    }

}
