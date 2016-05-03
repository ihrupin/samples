package com.hrupin.looptabs.infinitetabbar;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.hrupin.looptabs.looppager.LoopViewPager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Igor Khrupin www.hrupin.com on 03/05/16.
 */

public class TabInfiniteScrollView extends FrameLayout implements View.OnTouchListener {
    private static final String TAG = "TabInfiniteScrollView";

    private final static int SWIPE_OR_CLICK_ACTION_THRESHHOLD = 150;

    private static boolean touchDown = false;
    private final float tabWidth;

    private Context context;
    private ArrayList<TabCloneableView> carouselItems;
    private TabCloneableView replicaF, replicaB;
    private int itemCount = 0;
    private float scrollX;
    private float firstX;
    private float firstY;
    private TabSize itemSize;
    private boolean isScrolling = false;
    private LoopViewPager viewPager;

    public TabInfiniteScrollView(Context ctx, TabSize itemsize) {
        super(ctx);
        context = ctx;
        itemSize = itemsize;
        tabWidth = itemSize.width;
        RelativeLayout.LayoutParams sparams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.setClipChildren(true);
        this.setLayoutParams(sparams);
        this.setOnTouchListener(this);
        carouselItems = new ArrayList<TabCloneableView>();
        setHorizontalScrollBarEnabled(false);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        //return super.onInterceptHoverEvent(event);
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        //return super.onInterceptTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        switch (arg1.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = arg1.getX();
                firstY = arg1.getY();
                touchDown = true;
                if (isLeftTab()) {
                    //TODO handle tab state
                } else if (isRightTab()) {
                    //TODO handle tab state
                } else {
                    //TODO handle tab state
                }
                return true;
            case MotionEvent.ACTION_UP: {
                touchDown = false;
                float finishX = arg1.getX();
                float finishY = arg1.getY();
                if (isAClick(firstX, finishX, firstY, finishY)) {
                    if (isLeftTab()) {
                        swipeLeft(true);
                    } else if (isRightTab()) {
                        swipeRight(true);
                    }
                } else {
                    if (isLeftTab()) {
                        //TODO handle tab state
                    } else if (isRightTab()) {
                        //TODO handle tab state
                    } else {
                        //TODO handle tab state
                    }
                }
                firstX = 0;
                firstY = 0;
            }
            case MotionEvent.ACTION_OUTSIDE: {
                if (isLeftTab()) {
                    //TODO handle tab state
                } else if (isRightTab()) {
                    //TODO handle tab state
                } else {
                    //TODO handle tab state
                }
            }
            case MotionEvent.ACTION_MOVE:
                float finishX = arg1.getX();
                if (touchDown) {
                    float differenceX = firstX - finishX;
                    if (Math.abs(differenceX) > SWIPE_OR_CLICK_ACTION_THRESHHOLD) {
                        if (differenceX > 0) {
                            swipeRight(true);
                        } else {
                            swipeLeft(true);
                        }
                    }
                }
                break;
        }
        return false;
    }

    private boolean isRightTab() {
        return firstX > 2 * tabWidth && firstX < 3 * tabWidth;
    }

    private boolean isLeftTab() {
        return firstX > 0 && firstX < tabWidth;
    }

    public void swipeLeft(boolean triggerViewPager) {
        swipe(tabWidth);
        if(triggerViewPager) {
            viewPager.swipeLeft();
        }
    }

    public void swipeRight(boolean triggerViewPager) {
        swipe(-tabWidth);
        if(triggerViewPager) {
            viewPager.swipeRight();
        }
    }

    private void swipe(final float delta) {
        if (!isScrolling) {
            isScrolling = true;
            final float step = delta / 10f;
            scrollX = step;
            new ScrollTimerTask().run();
            final Handler handler = new Handler();
            final java.util.concurrent.atomic.AtomicInteger n = new AtomicInteger(1);
            final Runnable counter = new Runnable() {
                @Override
                public void run() {
                    if (n.getAndIncrement() == 11) {
                        isScrolling = false;
                        touchDown = false;
                        firstX = 0;
                        scrollX = 0;
                    } else {
                        new ScrollTimerTask().run();
                        handler.postDelayed(this, 10);
                    }
                }
            };
            handler.postDelayed(counter, 10);
        }
    }

    private boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        if (differenceX > SWIPE_OR_CLICK_ACTION_THRESHHOLD || differenceY > SWIPE_OR_CLICK_ACTION_THRESHHOLD) {
            return false;
        }
        return true;
    }

    public void setViewPager(LoopViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public class ScrollTimerTask implements Runnable {
        public synchronized void arrangeViews() {
            if (Math.abs(scrollX) > 0.1f) {
                //Log.i(TAG, "arrangeViews");
                synchronized (carouselItems) {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        public void run() {
                            Collections.sort(carouselItems,
                                    new Comparator<View>() {
                                        @Override
                                        public int compare(View lhs, View rhs) {
                                            FrameLayout.LayoutParams lhsParams = (FrameLayout.LayoutParams) lhs.getLayoutParams();
                                            FrameLayout.LayoutParams rhsParams = (FrameLayout.LayoutParams) rhs.getLayoutParams();
                                            return ((Integer) lhsParams.leftMargin).compareTo((Integer) rhsParams.leftMargin);
                                        }
                                    });
                            for (int i = 0; i < carouselItems.size(); i++) {
                                // arrange
                                TabCloneableView vw = carouselItems.get(i);
                                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) vw.getLayoutParams();
                                if (i == 0) {
                                    params.leftMargin += scrollX;
                                } else {
                                    FrameLayout.LayoutParams fparams = (FrameLayout.LayoutParams) carouselItems.get(0).getLayoutParams();
                                    params.leftMargin = (int) (fparams.leftMargin + (i * itemSize.width));
                                }
                                if (vw != replicaF && vw != replicaB) {
                                    if (scrollX > 0) {
                                        if (params.leftMargin > ((itemCount - 1) * itemSize.width) && params.leftMargin <= (itemCount * itemSize.width)) {
                                            FrameLayout.LayoutParams rparams = null;
                                            if (replicaF == null) {
                                                replicaF = vw.clone();
                                                rparams = new FrameLayout.LayoutParams((int) itemSize.width, (int) itemSize.height);
                                                rparams.leftMargin = (int) -itemSize.width;
                                                replicaF.setLayoutParams(rparams);
                                            }
                                            if (TabInfiniteScrollView.this.findViewById(replicaF.getId()) == null) {
                                                TabInfiniteScrollView.this.addView(replicaF);
                                                carouselItems.add(0, replicaF);
                                            }
                                        }
                                        if (params.leftMargin >= (itemCount * itemSize.width)) {
                                            if (replicaF != null) {
                                                if (TabInfiniteScrollView.this.findViewById(replicaF.getId()) != null) {
                                                    TabInfiniteScrollView.this.removeView(replicaF);
                                                    carouselItems.remove(replicaF);
                                                    carouselItems.remove(vw);
                                                    carouselItems.add(0, vw);
                                                    i = 0;
                                                    params.leftMargin = 0;
                                                }
                                            }
                                            replicaF = null;
                                        }
                                    } else {
                                        if (params.leftMargin < 0 && params.leftMargin >= -itemSize.width) {
                                            FrameLayout.LayoutParams rparams = null;
                                            if (replicaB == null) {
                                                replicaB = vw.clone();
                                                rparams = new FrameLayout.LayoutParams((int) itemSize.width, (int) itemSize.height);
                                                rparams.leftMargin = (int) ((itemCount - 1) * itemSize.width);
                                                replicaB.setLayoutParams(rparams);
                                            }
                                            if (TabInfiniteScrollView.this.findViewById(replicaB.getId()) == null) {
                                                TabInfiniteScrollView.this.addView(replicaB);
                                                carouselItems.add(replicaB);
                                            }
                                        }
                                        if (params.leftMargin < -itemSize.width) {
                                            if (replicaB != null) {
                                                if (TabInfiniteScrollView.this.findViewById(replicaB.getId()) != null) {
                                                    TabInfiniteScrollView.this.removeView(replicaB);
                                                    carouselItems.remove(replicaB);
                                                    carouselItems.remove(vw);
                                                    carouselItems.add(vw);
                                                    i = 0;
                                                    params.leftMargin = (int) (itemCount * itemSize.width);
                                                }
                                            }
                                            replicaB = null;
                                        }
                                    }
                                }
                                vw.setLayoutParams(params);
                            }

                            if (replicaF != null) {
                                FrameLayout.LayoutParams fparams = (FrameLayout.LayoutParams) replicaF.getLayoutParams();
                                if (fparams.leftMargin < -itemSize.width || fparams.leftMargin > 0) {
                                    if (TabInfiniteScrollView.this.findViewById(replicaF.getId()) != null) {
                                        replicaF.getLayoutParams();
                                        TabInfiniteScrollView.this.removeView(replicaF);
                                        carouselItems.remove(replicaF);
                                    }
                                    replicaF = null;
                                }
                            }
                            Collections.sort(carouselItems,
                                    new Comparator<View>() {
                                        @Override
                                        public int compare(View lhs, View rhs) {
                                            FrameLayout.LayoutParams lhsParams = (FrameLayout.LayoutParams) lhs.getLayoutParams();
                                            FrameLayout.LayoutParams rhsParams = (FrameLayout.LayoutParams) rhs.getLayoutParams();
                                            return ((Integer) lhsParams.leftMargin).compareTo((Integer) rhsParams.leftMargin);
                                        }

                                    });
                            Log.i(TAG, carouselItems + "");
                        }
                    });
                }
            }

        }

        @Override
        public void run() {
            arrangeViews();
        }
    }

    public void addItem(TabCloneableView vw) {
        FrameLayout.LayoutParams rparams = new FrameLayout.LayoutParams((int) itemSize.width, (int) itemSize.height);
        rparams.leftMargin = (int) (carouselItems.size() * itemSize.width);
        vw.setLayoutParams(rparams);
        super.addView(vw, carouselItems.size());
        carouselItems.add(vw);
        itemCount = carouselItems.size();
    }
}