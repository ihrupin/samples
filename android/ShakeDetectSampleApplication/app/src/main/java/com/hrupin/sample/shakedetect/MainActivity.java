package com.hrupin.sample.shakedetect;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.security.cert.CollectionCertStoreParameters;


public class MainActivity extends Activity implements SensorEventListener, View.OnClickListener {

    private long lastUpdate = -1;
    private float x, y, z;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 800;
    private SensorManager sensorMgr;
    private RelativeLayout background;
    private TextView text;
    private boolean isShaked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorMgr == null) {
            background = (RelativeLayout) findViewById(R.id.rlBackground);
            background.setOnClickListener(this);
            text = (TextView) findViewById(R.id.text);
            resetScreenUI();

            sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
            boolean accelSupported = sensorMgr.registerListener(this, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);

            if (!accelSupported) {
                // on accelerometer on this device
                sensorMgr.unregisterListener(this, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];

                float speed = Math.abs(x+y+z - last_x - last_y - last_z)
                        / diffTime * 10000;
                if (speed > SHAKE_THRESHOLD) {
                    // yes, this is a shake action! Do something about it!
                    isShaked = true;
                    background.setBackgroundColor(Color.RED);
                    text.setText("SHAKED!!! click for try again");
                    text.setTextColor(Color.YELLOW);
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onPause() {
        if (sensorMgr != null) {
            sensorMgr.unregisterListener(this, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
            sensorMgr = null;
            resetScreenUI();
        }
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        if(isShaked){
            resetScreenUI();
        }
    }

    private void resetScreenUI() {
        background.setBackgroundColor(Color.WHITE);
        text.setText(R.string.shake_me);
        text.setTextColor(Color.RED);
    }
}
