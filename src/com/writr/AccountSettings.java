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

public class AccountSettings extends SherlockActivity implements
		OnItemClickListener {

	ListView listView;
	PreferenceItemAdapter adapter;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_settings);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		populateList();
	}

	private void populateList() {
		// TODO Auto-generated method stub
		// Get array of the user's creations
		ArrayList<PreferenceItem> settings = new ArrayList<PreferenceItem>();
		// Create a for loop to populate the ArrayList
		settings.add(new PreferenceItem("Privacy", "Who sees what",
				R.drawable.privacy_icon));
		settings.add(new PreferenceItem("Delete Writr account",
				"Get rid of everything", R.drawable.delete_icon));
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
			i = new Intent(getApplicationContext(), PrivacyPreferences.class);
			startActivity(i);
			break;
		case 1:
			i = new Intent(getApplicationContext(), DeleteAccount.class);
			startActivity(i);
			break;
		}
	}

}
