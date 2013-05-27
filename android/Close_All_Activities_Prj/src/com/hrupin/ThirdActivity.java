package com.hrupin;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThirdActivity extends AppBaseActivity  implements OnClickListener  {
    private Button buttonCloseAllActivities;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        registerBaseActivityReceiver();
        buttonCloseAllActivities = (Button)findViewById(R.id.buttonCloseAllActivities);
        buttonCloseAllActivities.setOnClickListener(this);
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	unRegisterBaseActivityReceiver();
    }
    
	@Override
	public void onClick(View v) {
		/* THIS METHOD CLOSE ALL ACTIVITIES OF THIS APPLICATION. 
		 * Please, check this by click BACK button on your phone.*/
		closeAllActivities();
	}
}