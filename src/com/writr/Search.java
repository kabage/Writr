package com.writr;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;

public class Search extends SherlockFragmentActivity {

	ArrayAdapter<String> adapter;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getList();
	}

	private void getList() {
		// TODO Auto-generated method stub
		// Get the relevant ArrayList
		ArrayList<String> yourStuff = new ArrayList<String>();
		// Create a for loop to populate the ArrayList
		yourStuff.add("A-Creation");
		yourStuff.add("B-Creation");
		yourStuff.add("J-Creation");
		yourStuff.add("K-Creation");
		yourStuff.add("V-Creation");
		yourStuff.add("P-Creation");
		yourStuff.add("U-Creation");
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.list_item, R.id.label, yourStuff);
		listView = (ListView) findViewById(R.id.lvListItems);
		listView.setTextFilterEnabled(true);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String title = "The Hobbit";
				String creation = "You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely.";
				String authorName = "George R.R. Martin";
				String summary = "You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.*";
				String imageUrl = "http://www.red967fm.com/wp-content/uploads/2014/02/profileholder-150x150.gif";
				Intent i = new Intent(getApplicationContext(),
						NonEditableCreationView.class);
				Bundle b = new Bundle();
				b.putString("title", title);
				b.putString("creation", creation);
				b.putString("authorName", authorName);
				b.putString("summary", summary);
				b.putString("imageUrl", imageUrl);
				i.putExtras(b);
				startActivity(i);
			}
		});
	}

	public boolean onCreateOptionsMenu(final Menu menu) {
		getSupportMenuInflater().inflate(R.menu.search_menu, menu);
		menu.findItem(R.id.mSearchForCreation).expandActionView();
		SearchView searchView = (SearchView) menu.findItem(
				R.id.mSearchForCreation).getActionView();
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextChange(String query) {
				adapter.getFilter().filter(query);
				adapter.notifyDataSetChanged();
				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
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
