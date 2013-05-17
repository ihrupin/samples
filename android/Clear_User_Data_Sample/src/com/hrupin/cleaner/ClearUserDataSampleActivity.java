package com.hrupin.cleaner;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ClearUserDataSampleActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	Button btnClearData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnClearData = (Button) findViewById(R.id.buttonClearData);
		btnClearData.setOnClickListener(this);

		addUserDataInApplicationDir();
	}

	private void addUserDataInApplicationDir() {
		// Add shared preferences
		SharedPreferences settings = getSharedPreferences("sample", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("key1", true);
		editor.putString("key2", "Some strings in prefs");
		editor.commit();

		// Add file with content
		try {
			final String FILECONTENT = new String("This is string in file samplefile.txt");
			FileOutputStream fOut = openFileOutput("samplefile.txt", MODE_WORLD_READABLE);
			OutputStreamWriter osw = new OutputStreamWriter(fOut);
			osw.write(FILECONTENT);
			osw.flush();
			osw.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		MyApplication.getInstance().clearApplicationData();
	}
}