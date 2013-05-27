package com.hrupin.sample.filterlist;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class FilterListSampleActivity extends Activity implements TextWatcher {
    
    private static List<String> countries = Storage.getCountries();
    private EditText editTextFilter;
    private ListView listViewCountries;
    private ArrayAdapter<String> adapter;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        
        editTextFilter = (EditText)findViewById(R.id.editTextFilter);
        editTextFilter.addTextChangedListener(this);
        
        listViewCountries = (ListView)findViewById(R.id.listViewCountries);
        listViewCountries.setAdapter(adapter);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {}
    
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

}