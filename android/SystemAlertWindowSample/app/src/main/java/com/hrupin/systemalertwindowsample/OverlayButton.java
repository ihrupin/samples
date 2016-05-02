package com.hrupin.systemalertwindowsample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Igor Khrupin www.hrupin.com on 4/29/16.
 */
public class OverlayButton implements View.OnClickListener {
    private static final String TAG = "OverlayButton";

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    private final WindowManager mWindowManager;
    private final WindowManager.LayoutParams mOverlayParams;
    private Context mContext;
    private Button button;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsVisible = false;

    public OverlayButton(Context context) {
        mContext = context;

        remove();

        button = new Button(mContext);
        button.setId(generateViewId());
        button.setText("Send Toast");
        button.setTextColor(Color.RED);
        button.setOnClickListener(this);

        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);


        mOverlayParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, PixelFormat.TRANSLUCENT);

        mOverlayParams.gravity = Gravity.CENTER;

    }

    public void attachToWindow() {
        Log.d(TAG, "attachToWindow");

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (!mIsVisible && mOverlayParams != null) {
                    mWindowManager.addView(button, mOverlayParams);
                    mIsVisible = true;
                }
            }
        });
    }

    public void remove() {
        Log.d(TAG, "remove");

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mIsVisible) {
                    mWindowManager.removeView(button);
                    mIsVisible = false;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "Button removed", Toast.LENGTH_SHORT).show();
        remove();
    }

    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
