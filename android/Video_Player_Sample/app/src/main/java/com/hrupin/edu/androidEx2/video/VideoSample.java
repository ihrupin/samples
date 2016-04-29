package com.hrupin.edu.androidEx2.video;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.hrupin.edu.androidEx2.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class VideoSample extends Activity implements OnSeekBarChangeListener, Callback, OnPreparedListener, OnCompletionListener, OnBufferingUpdateListener,
        OnClickListener, OnSeekCompleteListener, AnimationListener {
    private static final String TAG = "VideoSample";
    private TextView textViewPlayed;
    private TextView textViewLength;
    private SeekBar seekBarProgress;
    private SurfaceView surfaceViewFrame;
    private ImageView imageViewPauseIndicator;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private ProgressBar progressBarWait;
    private Timer updateTimer;
    private Animation hideMediaController;
    private LinearLayout linearLayoutMediaController;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videosample);

        extras = getIntent().getExtras();

        linearLayoutMediaController = (LinearLayout) findViewById(R.id.linearLayoutMediaController);
        linearLayoutMediaController.setVisibility(View.GONE);

        hideMediaController = AnimationUtils.loadAnimation(this, R.anim.disapearing);
        hideMediaController.setAnimationListener(this);

        imageViewPauseIndicator = (ImageView) findViewById(R.id.imageViewPauseIndicator);
        imageViewPauseIndicator.setVisibility(View.GONE);
        if (player != null) {
            if (!player.isPlaying()) {
                imageViewPauseIndicator.setVisibility(View.VISIBLE);
            }
        }

        textViewPlayed = (TextView) findViewById(R.id.textViewPlayed);
        textViewLength = (TextView) findViewById(R.id.textViewLength);

        surfaceViewFrame = (SurfaceView) findViewById(R.id.surfaceViewFrame);
        surfaceViewFrame.setOnClickListener(this);
        surfaceViewFrame.setClickable(false);

        seekBarProgress = (SeekBar) findViewById(R.id.seekBarProgress);
        seekBarProgress.setOnSeekBarChangeListener(this);
        seekBarProgress.setProgress(0);

        progressBarWait = (ProgressBar) findViewById(R.id.progressBarWait);

        holder = surfaceViewFrame.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        player = new MediaPlayer();
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnBufferingUpdateListener(this);
        player.setOnSeekCompleteListener(this);
        player.setScreenOnWhilePlaying(true);
    }

    private void playVideo() {
        if (extras.getString("video_path").equals("VIDEO_URI")) {
            showToast("Please, set the video URI in the MainActivity.java");
        } else {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        player.setDataSource(extras.getString("video_path"));
                        player.prepare();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        showToast("Error while playing video");
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        showToast("Error while playing video");
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                        showToast("Error while playing video. Please, check your network connection.");
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void showToast(final String string) {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(VideoSample.this, string, Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void hideMediaController() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            linearLayoutMediaController.startAnimation(hideMediaController);
                        }
                    });
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.i(TAG, "========== onProgressChanged : " + progress + " from user: " + fromUser);
        if (!fromUser) {
            textViewPlayed.setText(Utils.durationInSecondsToString(progress));
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        if (player.isPlaying()) {
            progressBarWait.setVisibility(View.VISIBLE);
            player.seekTo(seekBar.getProgress() * 1000);
            Log.i(TAG, "========== SeekTo : " + seekBar.getProgress());
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub

    }

    public void surfaceCreated(SurfaceHolder holder) {
        player.setDisplay(holder);
        playVideo();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }

    public void onPrepared(MediaPlayer mp) {
        Log.i(TAG, "========== onPrepared ===========");
        int duration = mp.getDuration() / 1000; // duration in seconds
        seekBarProgress.setMax(duration);
        textViewLength.setText(Utils.durationInSecondsToString(duration));
        progressBarWait.setVisibility(View.GONE);

        // Get the dimensions of the video
        int videoWidth = player.getVideoWidth();
        int videoHeight = player.getVideoHeight();
        float videoProportion = (float) videoWidth / (float) videoHeight;
        Log.i(TAG, "VIDEO SIZES: W: " + videoWidth + " H: " + videoHeight + " PROP: " + videoProportion);

        // Get the width of the screen
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        float screenProportion = (float) screenWidth / (float) screenHeight;
        Log.i(TAG, "VIDEO SIZES: W: " + screenWidth + " H: " + screenHeight + " PROP: " + screenProportion);

        // Get the SurfaceView layout parameters
        android.view.ViewGroup.LayoutParams lp = surfaceViewFrame.getLayoutParams();

        if (videoProportion > screenProportion) {
            lp.width = screenWidth;
            lp.height = (int) ((float) screenWidth / videoProportion);
        } else {
            lp.width = (int) (videoProportion * (float) screenHeight);
            lp.height = screenHeight;
        }

        // Commit the layout parameters
        surfaceViewFrame.setLayoutParams(lp);

        // Start video
        if (!player.isPlaying()) {
            player.start();
            updateMediaProgress();
            linearLayoutMediaController.setVisibility(View.VISIBLE);
            hideMediaController();
        }
        surfaceViewFrame.setClickable(true);
    }

    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        if (updateTimer != null) {
            updateTimer.cancel();
        }
        finish();
    }

    /**
     * Change progress of mediaController
     */
    private void updateMediaProgress() {
        updateTimer = new Timer("progress Updater");
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        seekBarProgress.setProgress(player.getCurrentPosition() / 1000);
                    }
                });
            }
        }, 0, 1000);
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        int progress = (int) ((float) mp.getDuration() * ((float) percent / (float) 100));
        seekBarProgress.setSecondaryProgress(progress / 1000);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.surfaceViewFrame) {
            if (linearLayoutMediaController.getVisibility() == View.GONE) {
                linearLayoutMediaController.setVisibility(View.VISIBLE);
                hideMediaController();
            } else if (player != null) {
                if (player.isPlaying()) {
                    player.pause();
                    imageViewPauseIndicator.setVisibility(View.VISIBLE);
                } else {
                    player.start();
                    imageViewPauseIndicator.setVisibility(View.GONE);
                }
            }
        }
    }

    public void onSeekComplete(MediaPlayer mp) {
        progressBarWait.setVisibility(View.GONE);
    }

    public void onAnimationEnd(Animation animation) {
        // TODO Auto-generated method stub

    }

    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    public void onAnimationStart(Animation animation) {
        linearLayoutMediaController.setVisibility(View.GONE);
    }
}
