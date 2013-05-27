package com.hrupin.sample.groupindicator;

import android.view.View;
import android.widget.TextView;

public class ViewHolder {

    public TextView text;

    public ViewHolder(View v) {
        this.text = (TextView)v.findViewById(R.id.text);
    }

}
