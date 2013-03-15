package test.android.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onButtonClick(View view) {
		Button button = (Button) findViewById(R.id.clickMe);
		Toast.makeText(this, "Button updated", Toast.LENGTH_LONG).show();
		button.setText("HoHoHo"); 
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	
}