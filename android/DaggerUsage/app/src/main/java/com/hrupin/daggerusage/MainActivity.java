package com.hrupin.daggerusage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyApplication)getApplicationContext()).getMyComponent().inject(this);

        mySharedPreferences.putData("info",10);

        int fromPrefs = mySharedPreferences.getData("info");

        ((TextView)findViewById(R.id.text)).setText("From prefs: " + fromPrefs);
    }
}
