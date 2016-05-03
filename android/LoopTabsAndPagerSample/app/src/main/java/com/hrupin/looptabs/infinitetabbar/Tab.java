package com.hrupin.looptabs.infinitetabbar;

import com.hrupin.looptabs.R;

/**
 * Created by Igor Khrupin www.hrupin.com on 03/05/16.
 */
public enum Tab {
    TAB_1(R.drawable.ic_tab_1_act, R.drawable.ic_tab_1, 0),
    TAB_2(R.drawable.ic_tab_2_act, R.drawable.ic_tab_2, 1),
    TAB_3(R.drawable.ic_tab_3_act, R.drawable.ic_tab_3, 2);

    private final int resActive;
    private final int resInctive;
    private final int index;

    public int getResActive() {
        return resActive;
    }

    public int getResInctive() {
        return resInctive;
    }

    public int getIndex() {
        return index;
    }

    Tab(int resActive, int resInctive, int index) {
        this.resActive = resActive;
        this.resInctive = resInctive;
        this.index = index;
    }
}
