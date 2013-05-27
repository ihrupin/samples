package com.hrupin.sample.soundeffects;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SoundEffectsInAndroidActivity extends Activity implements OnClickListener {
    private Button button;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // init Effects class
        Effects.getInstance().init(this);
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Effects.getInstance().playSound(Effects.SOUND_1);
    }
}