package com.hrupin.sample.filterlist;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CountryListAdapter extends ArrayAdapter<Country> {
    private LayoutInflater vi;
    private static int mResource = R.layout.item_list;

    public CountryListAdapter(Activity activity, final List<Country> items) {
        super(activity, mResource, items);
        vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static class ViewHolder {
        private TextView textViewItemCountryName;
        private TextView textViewItemCountryArea;
        private TextView textViewItemCountryPopulation;

        public ViewHolder(View v) {
            this.textViewItemCountryName = (TextView) v.findViewById(R.id.textViewItemCountryName);
            this.textViewItemCountryArea = (TextView) v.findViewById(R.id.textViewItemCountryArea);
            this.textViewItemCountryPopulation = (TextView) v.findViewById(R.id.textViewItemCountryPopulation);
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = vi.inflate(mResource, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Country item = getItem(position);
        if (item != null) {
            holder.textViewItemCountryName.setText(Html.fromHtml(item.getName()));
            holder.textViewItemCountryArea.setText(Html.fromHtml("area = " + item.getArea() + " sq.km"));
            holder.textViewItemCountryPopulation.setText(Html.fromHtml("population = " + item.getPopulation()));

        }
        return convertView;
    }
}
