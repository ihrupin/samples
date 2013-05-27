package com.hrupin.sample.custonui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CustomScrollBarAndroidActivity extends Activity {
    private ListView list;
    private static List<String> listItems = initListItems();
    

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems));
    }


    private static List<String> initListItems() {
        List<String> list = new ArrayList<String>();
        boolean isNotFinish = true;
        int i = 0;
        while(isNotFinish){
            list.add("list item #" + i);
            if(i > 50){
                isNotFinish = false;
            }
            i++;
        }
        return list;
    }
}