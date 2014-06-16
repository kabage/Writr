package com.writr;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class Settings extends SherlockActivity implements OnItemClickListener {

	ListView listView;
	PreferenceItemAdapter adapter;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		populateList();
	}

	private void populateList() {
		// TODO Auto-generated method stub
		// Get array of the user's creations
		ArrayList<PreferenceItem> settings = new ArrayList<PreferenceItem>();
		// Create a for loop to populate the ArrayList
		settings.add(new PreferenceItem("Help", "Need help?",
				R.drawable.help_icon));
		settings.add(new PreferenceItem("About", "About us",
				R.drawable.about_icon));
		settings.add(new PreferenceItem("Notifications", "Sounds and stuff",
				R.drawable.notfication_icon));
		settings.add(new PreferenceItem("Profile", "Your info",
				R.drawable.profile_icon));
		settings.add(new PreferenceItem("Account", "Your Writr account",
				R.drawable.account_icon));

		adapter = new PreferenceItemAdapter(getApplicationContext(),
				R.layout.icon_list_item, settings);
		listView = (ListView) findViewById(R.id.lvListItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		switch (arg2) {
		case 0:
			i = new Intent(getApplicationContext(), Help.class);
			startActivity(i);
			break;
		case 1:
			i = new Intent(getApplicationContext(), About.class);
			startActivity(i);
			break;
		case 2:
			i = new Intent(getApplicationContext(),
					NotificationPreferences.class);
			startActivity(i);
			break;
		case 3:
			i = new Intent(getApplicationContext(), ProfilePreferences.class);
			startActivity(i);
			break;
		case 4:
			i = new Intent(getApplicationContext(), AccountSettings.class);
			startActivity(i);
			break;
		}
	}

}
