package com.hrupin.wifiSample;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;
import com.hrupin.wifiSample.R;

public class WifiSwitcherExampleActivity extends Activity implements OnClickListener {
    private static ConnectivityManager connectivityManager;
    private ToggleButton tbWiFi;
    private WifiManager wifi;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        tbWiFi = (ToggleButton) findViewById(R.id.toggleButtonWiFi);
        tbWiFi.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initButton();
    }

    private void initButton() {
        if (isConnected(this, ConnectivityManager.TYPE_WIFI)) {
            tbWiFi.setChecked(true);
        }
    }

    private static boolean isConnected(Context context, int networkType) {
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getNetworkInfo(networkType);
        }
        return networkInfo == null ? false : networkInfo.isConnected();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == tbWiFi.getId()) {
            wifi.setWifiEnabled(tbWiFi.isChecked());
        } 
    }
}