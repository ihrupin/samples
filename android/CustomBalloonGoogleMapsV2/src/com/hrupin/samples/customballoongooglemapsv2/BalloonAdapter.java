/***
  Copyright (c) 2012 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  From _The Busy Coder's Guide to Android Development_
    http://commonsware.com/Android
 */

package com.hrupin.samples.customballoongooglemapsv2;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class BalloonAdapter implements InfoWindowAdapter {
	LayoutInflater inflater = null;
	private TextView textViewName;
	private TextView textViewAddress;
	private TextView textViewInformation;
	private ImageView imageViewLogo;

	public BalloonAdapter(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		return (null);
	}

	@Override
	public View getInfoContents(Marker marker) {
		View v = inflater.inflate(R.layout.balloon_overlay, null);
		textViewAddress = (TextView) v.findViewById(R.id.balloonAddress);
		textViewName = (TextView) v.findViewById(R.id.balloonName);
		textViewInformation = (TextView) v
				.findViewById(R.id.balloonInformation);
		imageViewLogo = (ImageView) v.findViewById(R.id.imageViewLogo);

		setData(marker);

		return (v);
	}

	public void setData(Marker marker) {
		String title = marker.getTitle();
		String addressString = marker.getSnippet();
		String snippet = marker.getSnippet();

		textViewName.setVisibility(View.VISIBLE);
		textViewName.setText(title);
		textViewAddress.setVisibility(View.VISIBLE);
		textViewAddress.setText(addressString);

		textViewInformation.setVisibility(View.GONE);
		if (snippet != null) {
			textViewInformation.setVisibility(View.VISIBLE);
			textViewInformation.setText(snippet);
		}
		imageViewLogo.setVisibility(View.VISIBLE);
		imageViewLogo.setImageResource(R.drawable.ic_launcher);
	}
}