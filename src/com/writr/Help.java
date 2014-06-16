package com.writr;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class Help extends SherlockActivity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		populateList();
	}

	private void populateList() {
		// TODO Auto-generated method stub
		ArrayList<PreferenceItem> helpItems = new ArrayList<PreferenceItem>();
		// Create a for loop to populate the ArrayList
		helpItems.add(new PreferenceItem("FAQ", "Any questions?",
				R.drawable.faq_icon));
		PreferenceItemAdapter adapter = new PreferenceItemAdapter(
				getApplicationContext(), R.layout.icon_list_item, helpItems);
		ListView listView = (ListView) findViewById(R.id.lvListItems);
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
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		switch (arg2) {
		case 0:
			Intent i = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.twitter.com"));
			startActivity(i);
			break;
		}
	}

}
