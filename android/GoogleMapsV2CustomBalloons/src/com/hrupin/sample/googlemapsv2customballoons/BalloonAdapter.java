/*******************************************************************************
 * Copyright 2013 Igor Khrupin, www.hrupin.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/**
 * 
 */
package com.hrupin.sample.googlemapsv2customballoons;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class BalloonAdapter implements InfoWindowAdapter {
	LayoutInflater inflater = null;
	private TextView textViewTitle;

	public BalloonAdapter(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		View v = inflater.inflate(R.layout.balloon, null);
		if (marker != null) {
			textViewTitle = (TextView) v.findViewById(R.id.textViewTitle);
			textViewTitle.setText(marker.getTitle());
		}
		return (v);
	}

	@Override
	public View getInfoContents(Marker marker) {
		return (null);
	}
}
