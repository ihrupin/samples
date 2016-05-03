package com.hrupin.looptabs.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hrupin.looptabs.R;

/**
 * Created by Igor Khrupin www.hrupin.com on 03/05/16.
 */
public class Tab1Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment, null);

        TextView tv = (TextView) v.findViewById(R.id.tv);
        tv.setBackgroundColor(Color.GRAY);
        tv.setText("TAB_1");
        return v;
    }
}
