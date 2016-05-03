package com.hrupin.looptabs;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.hrupin.looptabs.infinitetabbar.TabBar;
import com.hrupin.looptabs.looppager.LoopViewPager;
import com.hrupin.looptabs.looppager.TabAdapter;

/**
 * Created by Igor Khrupin www.hrupin.com on 03/05/16.
 */

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "MainActivity";

    private LoopViewPager viewPager;
    private TabAdapter adapter;
    private int tabWidth;
    private int tabHeight;
    private TabBar tabBar;
    private int lastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (LoopViewPager) findViewById(R.id.pager);
        viewPager.setBoundaryCaching(true);
        adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabBar = (TabBar) findViewById(R.id.tabbar);

        initTabs();
    }

    private void initTabs() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        tabWidth = screenWidth/3;
        tabHeight = getResources().getDimensionPixelSize(R.dimen.bar_height);

        tabBar.post(new Runnable() {
            @Override
            public void run() {
                  tabBar.init(MainActivity.this, tabWidth, tabHeight, viewPager);
            }
        });

        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(lastPage > position) {
            //User Move to left
            tabBar.swipeLeft();
        }else if(lastPage < position) {
            //User Move to right
            tabBar.swipeRight();
        }
        lastPage=position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
