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

public class DiscoverView extends SherlockActivity {

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
		// Get array of the published creations
		ArrayList<Item> creationView = new ArrayList<Item>();
		creationView.add(new Item("Creation", "Creation Description"));
		creationView.add(new Item("Creation", "Creation Description"));
		creationView.add(new Item("Creation", "Creation Description"));

		ItemAdapter adapter = new ItemAdapter(getApplicationContext(),
				R.layout.list_item, creationView);
		ListView listView = (ListView) findViewById(R.id.lvListItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// Use position to get the selected item and its contents,
				// the creation and it's ID

				String title = "Don Quixote";
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent i = new Intent(getApplicationContext(), Discover.class);
			startActivity(i);
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
