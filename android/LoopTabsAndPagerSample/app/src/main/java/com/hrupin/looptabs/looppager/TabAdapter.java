package com.hrupin.looptabs.looppager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hrupin.looptabs.fragments.Tab1Fragment;
import com.hrupin.looptabs.fragments.Tab3Fragment;
import com.hrupin.looptabs.fragments.Tab2Fragment;

/**
 * Created by Igor Khrupin www.hrupin.com on 03/05/16.
 */
public class TabAdapter extends FragmentPagerAdapter {
    public static final int PAGE_COUNT = 21;
    public static final int NEARBY_INDEX = ((PAGE_COUNT + 2) / 2) + 2;
    public static final int MAP_INDEX = ((PAGE_COUNT + 2) / 2) + 1;

    public static final String LIST_SCREEN_INDEX = "0";
    public static final String MAP_SCREEN_INDEX = "1";

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        position = LoopViewPager.toRealPosition(position, getCount()) % 3;
        switch (position) {
            case 0:
                return new Tab1Fragment();
//                return Tab1Fragment.getInstance();
            case 1:
                return new Tab2Fragment();
//                return Tab2Fragment.getInstance();
            case 2:
                return new Tab3Fragment();
//                return Tab3Fragment.getInstance();
            default:
                return new Tab3Fragment();
        }
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}