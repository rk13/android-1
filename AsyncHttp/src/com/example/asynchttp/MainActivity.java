package com.example.asynchttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/**
 * Demo getting JSON over HTTP in a background task of an Android Activity. 
 * Be warned that it uses HttpURLConnection, which was somewhat buggy prior to 2.3. 
 * It also uses getLastNonConfigurationInstance() instead of the recommended 
 * Fragment.setRetainInstance() for simplicity sake.
 * 
 * Put your service URL in HTTP_URL constant. It shall be something producing a line 
 * like this: 
 *     { "patient": { "name":"Samuel", "surname":"Vimes" } } 
 * 
 * @author AleksejsTruhans
 * 
 */
public class MainActivity extends Activity {

	private static final String HTTP_URL = "http://172.18.1.94:8080/AndroidBackend/DataService";
	private TextView responseView;
	private HttpTask httpTask;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		responseView = (TextView) findViewById(R.id.responseView);
		// Google recommends using Fragment.setRetainInstance(),
		// yet it seems too complex for this demonstration purposes
		httpTask = (HttpTask) getLastNonConfigurationInstance();  
	}
	
	public void goButtonClicked(View view) {
		responseView.setText("Starting HTTP request...");
		launchHttpRequest();
	}

	private void launchHttpRequest() {
		httpTask = new HttpTask(); 
		httpTask.setResponseView(responseView);	
		httpTask.execute(HTTP_URL);
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

