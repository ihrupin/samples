package com.hrupin.looptabs.infinitetabbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Igor Khrupin www.hrupin.com on 03/05/16.
 */

public abstract class TabCloneableView extends RelativeLayout {

	public TabCloneableView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public TabCloneableView(Context context, AttributeSet attrs) {
		super(context,attrs);
		
		// TODO Auto-generated constructor stub
	}
	public abstract TabCloneableView clone();
}
