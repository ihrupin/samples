package com.hrupin.sample.soundeffects;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.widget.Toast;

public class Effects {

    private static final String TAG = Effects.class.toString();

    private static final Effects INSTANCE = new Effects();

    // Sound ID can't be 0 (zero)
    public static final int SOUND_1 = 1;

    private Effects() {

    }

    public static Effects getInstance() {
        return INSTANCE;
    }

    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap;
    int priority = 1;
    int no_loop = 0;
    private int volume;
    float normal_playback_rate = 1f;

    private Context context;

    public void init(Context context) {
        this.context = context;
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(SOUND_1, soundPool.load(context, R.raw.sound_1, 1));
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        volume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
    }

    public void playSound(int soundId) {
        Log.i(TAG, "!!!!!!!!!!!!!! playSound_1 !!!!!!!!!!");
        soundPool.play(soundId, volume, volume, priority, no_loop, normal_playback_rate);
        Toast.makeText(context, "Now you can hear sound effect!", Toast.LENGTH_LONG).show();
    }
}
