package com.hrupin.drawablesizecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements TextWatcher, OnTouchListener {

    private EditText etLdpi;
    private EditText etMdpi;
    private EditText etHdpi;
    private EditText etXhdpi;
    private EditText etXxhdpi;
    
    private MyTextWatcher twLdpi;
    private MyTextWatcher twMdpi;
    private MyTextWatcher twHdpi;
    private MyTextWatcher twXhdpi;
    private MyTextWatcher twXxhdpi;
    
    private float CONST_1333 = 1f + (1f/3f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        etLdpi = (EditText)findViewById(R.id.editTextLdpi);
        etLdpi.setOnTouchListener(this);
        
        etMdpi = (EditText)findViewById(R.id.editTextMdpi);
        etMdpi.setOnTouchListener(this);
        
        etHdpi = (EditText)findViewById(R.id.editTextHdpi);
        etHdpi.setOnTouchListener(this);
        
        etXhdpi = (EditText)findViewById(R.id.editTextXhdpi);
        etXhdpi.setOnTouchListener(this);
        
        etXxhdpi = (EditText)findViewById(R.id.editTextXxhdpi);
        etXxhdpi.setOnTouchListener(this);
        
        twLdpi = new MyTextWatcher(etLdpi);
        twMdpi = new MyTextWatcher(etMdpi);
        twHdpi = new MyTextWatcher(etHdpi);
        twXhdpi = new MyTextWatcher(etXhdpi);
        twXxhdpi = new MyTextWatcher(etXxhdpi);
        
        
    }
    
    
    class MyTextWatcher implements TextWatcher{

        private EditText mE;

        public MyTextWatcher(EditText e) {
            mE = e;
        }
        
        @Override
        public void afterTextChanged(Editable s) {
            
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            String str = s.toString();
            if(TextUtils.isEmpty(s.toString())){
                str = "0";
            }
            float i = Float.parseFloat(str);
            if(mE.getId() == etLdpi.getId()){
                float mdpi = i * CONST_1333;
                etMdpi.setText(mdpi + "");
                etHdpi.setText(mdpi * 1.5f + "");
                etXhdpi.setText(mdpi * 2f + "");
                etXxhdpi.setText(mdpi * 3f + "");
            }
            if(mE.getId() == etMdpi.getId()){
                float mdpi = i;
                etLdpi.setText(mdpi / CONST_1333 + "");
                etHdpi.setText(mdpi * 1.5f + "");
                etXhdpi.setText(mdpi * 2f + "");
                etXxhdpi.setText(mdpi * 3f + "");
            }
            if(mE.getId() == etHdpi.getId()){
                float mdpi = i / 1.5f;
                etLdpi.setText(mdpi / CONST_1333 + "");
                etMdpi.setText(mdpi + "");
                etXhdpi.setText(mdpi * 2f + "");
                etXxhdpi.setText(mdpi * 3f + "");
            }
            if(mE.getId() == etXhdpi.getId()){
                float mdpi = i / 2f;
                etLdpi.setText(mdpi / CONST_1333 + "");
                etMdpi.setText(mdpi + "");
                etHdpi.setText(mdpi * 1.5f + "");
                etXxhdpi.setText(mdpi * 3f + "");
            }
            if(mE.getId() == etXxhdpi.getId()){
                float mdpi = i / 3f;
                etLdpi.setText(mdpi / CONST_1333 + "");
                etMdpi.setText(mdpi + "");
                etHdpi.setText(mdpi * 1.5f + "");
                etXhdpi.setText(mdpi * 2f + "");
            }
        }
        
    }
    

    @Override
    public void afterTextChanged(Editable s) {
        Toast.makeText(this, etLdpi.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.getId() == etLdpi.getId()){
            clearTextWatchers();
            
            etLdpi.addTextChangedListener(twLdpi);
        }
        if(v.getId() == etMdpi.getId()){
            clearTextWatchers();
            
            etMdpi.addTextChangedListener(twMdpi);
        }
        if(v.getId() == etHdpi.getId()){
            clearTextWatchers(); 
            
            etHdpi.addTextChangedListener(twHdpi);
        }
        if(v.getId() == etXhdpi.getId()){
            clearTextWatchers();
            
            etXhdpi.addTextChangedListener(twXhdpi);
        }
        if(v.getId() == etXxhdpi.getId()){
            clearTextWatchers();
            
            etXxhdpi.addTextChangedListener(twXxhdpi);
        }
        return false;
    }

    private void clearTextWatchers() {
        etLdpi.removeTextChangedListener(twLdpi);
        etMdpi.removeTextChangedListener(twMdpi);
        etHdpi.removeTextChangedListener(twHdpi);
        etXhdpi.removeTextChangedListener(twXhdpi);
        etXxhdpi.removeTextChangedListener(twXxhdpi);
    }
}
