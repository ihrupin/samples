package com.hrupin.sample.custonui.fastscroll;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnCheckedChangeListener {
    private ListView list;
	private CheckBox checkBoxFastscroll;
    private static List<String> listItems = initListItems();
    

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems));
        
        checkBoxFastscroll = (CheckBox)findViewById(R.id.checkBoxFastscroll);
        checkBoxFastscroll.setOnCheckedChangeListener(this);
        checkBoxFastscroll.setChecked(true);
    }


    private static List<String> initListItems() {
        List<String> list = new ArrayList<String>();
        boolean isNotFinish = true;
        int i = 0;
        while(isNotFinish){
            list.add("list item #" + i);
            if(i > 100){
                isNotFinish = false;
            }
            i++;
        }
        return list;
    }


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		list.setFastScrollEnabled(isChecked);
	}
}