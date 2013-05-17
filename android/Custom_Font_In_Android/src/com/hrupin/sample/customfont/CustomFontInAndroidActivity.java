package com.hrupin.sample.customfont;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class CustomFontInAndroidActivity extends Activity {
    private static Typeface typeFace = null;
    private TextView textView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initTypeFace(this);
        textView = (TextView) findViewById(R.id.text);
        textView.setText(" Hello, my name is Igor Khrupin.\n\n I can help you in Android development.\n\n Contact me:\n www.hrupin.com");
        textView.setTypeface(typeFace);
    }

    public static void initTypeFace(Context context) {
        if (typeFace == null) {
            try {
                typeFace = Typeface.createFromAsset(context.getAssets(), "AeroviasBrasilNF.ttf");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}