package com.hrupin.samples.loadermanagersample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

	public static String EXTRA_URL = "extra_url"; 
	
    private TextView textView;
	private ProgressBar progressBar;
	private String url;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my, null);

        textView = (TextView) v.findViewById(R.id.textView);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        return v;
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Bundle args = getArguments();
		url = args.getString(EXTRA_URL);
		showProgress();
		Bundle bundle = new Bundle();
		getLoaderManager().initLoader(0, bundle, this);
	}

    private void showProgress() {
		if(textView != null){
			textView.setVisibility(View.INVISIBLE);
		}
		if(progressBar != null){
			progressBar.setVisibility(View.VISIBLE);
		}
	}
    
    private void hideProgress() {
		if(textView != null){
			textView.setVisibility(View.VISIBLE);
		}
		if(progressBar != null){
			progressBar.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public Loader<String> onCreateLoader(int arg0, Bundle arg1) {
		return new MyLoader(getActivity(), url);
	}

	@Override
	public void onLoadFinished(Loader<String> arg0, String arg1) {
		textView.setText(arg1);
		hideProgress();
	}

	@Override
	public void onLoaderReset(Loader<String> arg0) {
		// TODO Auto-generated method stub
		
	}
}
