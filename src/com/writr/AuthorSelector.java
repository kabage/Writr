package com.writr;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class AuthorSelector extends SherlockActivity {

	ArrayList<String> coCreators;
	ListView lvCreatorList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.author_selector);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setUp();
	}

	private void setUp() {
		// TODO Auto-generated method stub
		lvCreatorList = (ListView) findViewById(R.id.lvCreatorList);
		lvCreatorList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		populateList();
	}

	private void getCheckedItems() {
		// TODO Auto-generated method stub
		coCreators = new ArrayList<String>();
		SparseBooleanArray sba = lvCreatorList.getCheckedItemPositions();
		for (int i = 0; i < lvCreatorList.getCount(); i++) {
			if (sba.get(i) == true) {
				coCreators.add(lvCreatorList.getItemAtPosition(i).toString());
			}
		}

	}

	private void populateList() {
		// TODO Auto-generated method stub
		ArrayList<String> yourStuff = new ArrayList<String>();
		yourStuff.add("Friend One");
		yourStuff.add("Friend Two");
		yourStuff.add("Friend Three");
		yourStuff.add("Friend Four");
		yourStuff.add("Friend Five");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_list_item_multiple_choice, yourStuff);
		lvCreatorList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.author_selector_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.mStartCreation:
			getCheckedItems();
			finish();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
