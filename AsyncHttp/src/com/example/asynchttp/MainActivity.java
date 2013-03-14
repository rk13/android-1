package com.example.asynchttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView responseView;
	private HttpTask httpTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		responseView = (TextView) findViewById(R.id.responseView);
		httpTask = (HttpTask) getLastNonConfigurationInstance();  
	}
	
	public void goButtonClicked(View view) {
		responseView.setText("Starting HTTP request...");
		launchHttpRequest();
	}

	private void launchHttpRequest() {
		httpTask = new HttpTask(); 
		httpTask.setResponseView(responseView);	
		httpTask.execute("http://172.18.1.94:8080/AndroidBackend/DataService");
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return httpTask;
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (httpTask != null) { 
			httpTask.setResponseView(null);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (httpTask != null) { 
			httpTask.setResponseView(responseView);
		}
	}

	
	
	
	
}

