package com.hrupin.sample.groupindicator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class CustomGroupIndicatorForExpandableListViewActivity extends Activity {
    /** Called when the activity is first created. */
    
    private static final String[][] data = {{"child-1", "child-2", "child-3"},{"child-1", "child-2", "child-3"}};
    private ExpandableListView expandableListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView1);
        expandableListView.setAdapter(new SampleExpandableListAdapter(this, data));
    }
}