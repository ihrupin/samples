package com.hrupin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends AppBaseActivity implements OnClickListener {
    private Button buttonOpenNextActivity;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
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
		/* OPEN THIRD ACTIVITY.*/
		startActivity(new Intent(this, ThirdActivity.class));
	}
}