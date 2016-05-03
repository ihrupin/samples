package com.hrupin.looptabs.infinitetabbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.hrupin.looptabs.R;
import com.hrupin.looptabs.looppager.LoopViewPager;

/**
 * Created by Igor Khrupin www.hrupin.com on 03/05/16.
 */
public class TabBar extends RelativeLayout {

    private static final String TAG = "TabBar";
    private RelativeLayout container;
    private TabInfiniteScrollView scrollView;
    private LoopViewPager viewPager;


    public TabBar(Context context) {
        this(context, null);
    }

    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.layout_tab_bar, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.container = (RelativeLayout) findViewById(R.id.container);
    }

    public void init(Context context, int tabWidth, int tabHeight, LoopViewPager viewPager){
        scrollView = new TabInfiniteScrollView(context, new TabSize(tabWidth, tabHeight));

        scrollView.addItem(addTab(context, tabWidth, tabHeight, Tab.TAB_1));
        scrollView.addItem(addTab(context, tabWidth, tabHeight, Tab.TAB_2));
        scrollView.addItem(addTab(context, tabWidth, tabHeight, Tab.TAB_3));

        container.addView(scrollView);

        this.viewPager = viewPager;
        if(scrollView != null){
            scrollView.setViewPager(viewPager);
        }
    }

    private TabView addTab(Context context, int tabWidth, int tabHeight, Tab tab) {
        TabView t = new TabView(context);
        t.setId((int) (Math.random() * 10000));
        t.setTab(tab);
        t.switchState(TabView.State.INACTIVE);
        t.setLayoutParams(new RelativeLayout.LayoutParams(tabWidth, tabHeight));
        return t;
    }

    public void swipeLeft(){
        if(scrollView != null){
            scrollView.swipeLeft(false);
        }
    }

    public void swipeRight(){
        if(scrollView != null){
            scrollView.swipeRight(false);
        }
    }

}