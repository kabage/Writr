package com.writr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

public class PublishCreation extends SherlockActivity {

	Button bPublish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.publish_creation);
		setSupportProgressBarIndeterminateVisibility(false);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setUp();
	}

	private void setUp() {
		// TODO Auto-generated method stub
		bPublish = (Button) findViewById(R.id.bPublish);
		bPublish.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				publishCreation();
				setSupportProgressBarIndeterminateVisibility(true);
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.toast,
						(ViewGroup) findViewById(R.id.toast_layout_root));
				TextView text = (TextView) layout.findViewById(R.id.text);
				text.setText("Published!");
				Toast toast = new Toast(getApplicationContext());
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setDuration(Toast.LENGTH_SHORT);
				toast.setView(layout);
				toast.show();
				Intent i = new Intent(getApplicationContext(), Dashboard.class);
				startActivity(i);
			}
		});
	}

	private void publishCreation() {
		// TODO Auto-generated method stub
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		String privacyPreferenceString = prefs.getString("publish", "1");
		int privacyPreference = Integer.parseInt(privacyPreferenceString);
		switch (privacyPreference) {
		case 1:
			Toast.makeText(getApplicationContext(), "Everyone",
					Toast.LENGTH_LONG).show();
			break;
		case 2:
			Toast.makeText(getApplicationContext(), "Only friends",
					Toast.LENGTH_LONG).show();
			break;
		case 3:
			Toast.makeText(getApplicationContext(), "No one", Toast.LENGTH_LONG)
					.show();
			break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
