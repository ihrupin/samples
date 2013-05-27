package com.hrupin;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabActivitySampleActivity extends TabActivity {
	
	private static TabActivitySampleActivity INSTANCE;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        INSTANCE = this;
        TabHost host = getTabHost();
        
        TabSpec tab1 = host.newTabSpec("tab1");
        tab1.setIndicator("TAB 1");
        tab1.setContent(new Intent(this, FirstTabActivity.class));
        
        TabSpec tab2 = host.newTabSpec("tab2");
        tab2.setIndicator("TAB 2");
        tab2.setContent(new Intent(this, SecondTabActivity.class));
        
        host.addTab(tab1);
        host.addTab(tab2);
    }
    
    public static TabActivitySampleActivity getInstance(){
    	return INSTANCE;
    }
}