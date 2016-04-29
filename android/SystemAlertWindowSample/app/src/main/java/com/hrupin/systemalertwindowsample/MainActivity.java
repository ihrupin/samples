package com.hrupin.systemalertwindowsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Igor Khrupin www.hrupin.com on 4/29/16.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn_show_button);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        App.getInstance().showButton();
        finish();
    }
}
