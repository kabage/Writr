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

public class Discover extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.discover);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		populateList();
	}

	private void populateList() {
		// TODO Auto-generated method stub
		ArrayList<Item> creationCategories = new ArrayList<Item>();
		creationCategories.add(new Item("Vanity Cards", "Creation description"));
		creationCategories.add(new Item("Blogs", "Creation description"));
		creationCategories.add(new Item("Instructables", "Creation description"));
		creationCategories.add(new Item("Fun", "Creation description"));
		creationCategories.add(new Item("Discussions", "Creation description"));
		creationCategories.add(new Item("Scripts", "Creation description"));
		creationCategories.add(new Item("Lyrics", "Creation description"));
		creationCategories.add(new Item("Books", "Creation description"));
		creationCategories.add(new Item("Reviews", "Creation description"));
		ItemAdapter adapter = new ItemAdapter(getApplicationContext(),
				R.layout.list_item, creationCategories);
		ListView listView = (ListView) findViewById(R.id.lvListItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						DiscoverView.class);
				startActivity(i);
			}
		});
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
