package com.hrupin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends AppBaseActivity implements OnClickListener {
    /** Called when the activity is first created. */
	
	private Button buttonOpenNextActivity;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        registerBaseActivityReceiver();
        buttonOpenNextActivity = (Button)findViewById(R.id.buttonOpenNextActivity);
        buttonOpenNextActivity.setOnClickListener(this);
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	unRegisterBaseActivityReceiver();
    }

	@Override
	public void onClick(View v) {
		/* OPEN SECOND ACTIVITY.*/
		startActivity(new Intent(this, SecondActivity.class));
	}
}