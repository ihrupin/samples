package com.hrupin.looptabs.infinitetabbar;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hrupin.looptabs.R;

/**
 * Created by Igor Khrupin www.hrupin.com on 03/05/16.
 */
public class TabView extends TabCloneableView {

    private static final String TAG = "TabView";

    public enum State {
        INACTIVE, INACTIVE_PRESSED, ACTIVE, ACTIVE_PRESSED;
    }

    private Tab tab;
    private State state = State.INACTIVE;
    private ImageView ivInActive;
    private ImageView ivActive;
    private TextView tvIndex;

    public TabView(Context context) {
        super(context);
        View.inflate(context, R.layout.item_tab, this);
        this.ivInActive = (ImageView) findViewById(R.id.iv_inactive);
        this.ivActive = (ImageView) findViewById(R.id.iv_active);
        this.tvIndex = (TextView) findViewById(R.id.index);
    }

    public TabView clone() {
        TabView vw = new TabView(this.getContext());
        vw.switchState(State.INACTIVE);
        vw.setId((int) (Math.random() * 10000));
        vw.setTag(this.getTag());
        vw.setTab(this.getTab());
        return vw;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
        this.ivActive.setImageResource(tab.getResActive());
        this.ivInActive.setImageResource(tab.getResInctive());
        this.tvIndex.setText(tab.getIndex() + "");
    }

    public Tab getTab() {
        return tab;
    }

    public State getState() {
        return state;
    }

    public void switchState(State state) {
        this.state = state;
        switch (state) {
            case ACTIVE:
                this.ivActive.setAlpha(1f);
                this.ivInActive.setAlpha(0f);
                break;
            case INACTIVE:
                this.ivActive.setAlpha(0f);
                this.ivInActive.setAlpha(1f);
                break;
            case ACTIVE_PRESSED:
                this.ivActive.setAlpha(0.6f);
                this.ivInActive.setAlpha(0f);
                break;
            case INACTIVE_PRESSED:
                this.ivActive.setAlpha(0f);
                this.ivInActive.setAlpha(0.6f);
                break;
        }
    }

    public void setPositionOffset(boolean isNext, float positionOffset) {
        float activeAlpha = positionOffset;
        float inActiveAlpha = 1f - positionOffset;
        if (isNext) {
            this.ivActive.setAlpha(activeAlpha);
            this.ivInActive.setAlpha(inActiveAlpha);
        } else {
            this.ivActive.setAlpha(inActiveAlpha);
            this.ivInActive.setAlpha(activeAlpha);
        }
    }

    @Override
    public String toString() {
        return "TV{" +
                "t=" + tab +
                //", state=" + state +
                ",i=" + tab.getIndex() +
                '}';
    }
}