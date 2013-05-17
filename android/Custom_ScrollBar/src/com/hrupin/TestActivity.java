package com.hrupin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TestActivity extends Activity implements OnClickListener {
	private ListView list;
	private Button buttonScroll;
	private static final String[] items = { "Vasia", "Petia", "Serioja", "Somebody name", "Igor", "Khrupin", "Test", "Vasia", "Petia", "Serioja",
			"Somebody name", "Igor", "Khrupin", "Test", "Vasia", "Petia", "Serioja", "Somebody name", "Igor", "Khrupin", "Test", "Vasia", "Petia", "Serioja",
			"Somebody name", "Igor", "Khrupin", "Test" };
	private ListAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

		list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(adapter);
		buttonScroll = (Button) findViewById(R.id.buttonScrollToTop);
		buttonScroll.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Toast.makeText(this, "Scroll...", Toast.LENGTH_SHORT).show();
		list.smoothScrollToPosition(0);
	}
}