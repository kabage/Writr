package com.writr;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class FriendCreationView extends SherlockActivity {

	ItemAdapter adapter;
	ListView listView;
	String friendId;
	ArrayList<String> friendCreationTitleArray, friendCreationAuthorArray,
			friendCreationSummary, friendCreationContent,
			friendCreationAuthorUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getData();
		populateList();
	}

	private void getData() {
		// TODO Auto-generated method stub
		Bundle b = getIntent().getExtras();
		friendId = b.getString("friendId");
		Log.i("the friend jid", friendId);

		// 1. Look for the friend ID class name. 2. Look for the list of the
		// stories. 3. Populate the ArrayLists.
	}

	private void populateList() {
		// TODO Auto-generated method stub
		ArrayList<Item> friendList = new ArrayList<Item>();

		// Create a for loop to populate the ArrayList
		// for (int i = 0; i < friendCreationTitleArray.size(); i++) {
		// userCreation.add(new Item(friendCreationTitleArray.get(i),
		// friendCreationDescriptionArray.get(i)));
		// }

		friendList.add(new Item("Friend Creation", "Friend creation summary"));
		friendList.add(new Item("Friend Creation", "Friend creation summary"));
		friendList.add(new Item("Friend Creation", "Friend creation summary"));

		adapter = new ItemAdapter(getApplicationContext(), R.layout.list_item,
				friendList);
		listView = (ListView) findViewById(R.id.lvListItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				// String title = friendCreationTitleArray.get(arg2);
				// String creation = friendCreationContent.get(arg2);
				// String authorName = friendCreationAuthorArray.get(arg2);
				// String summary = friendCreationSummary.get(arg2);
				// String imageUrl = friendCreationAuthorUrl.get(arg2);

				String title = "Lord of the Rings";
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater()
				.inflate(R.menu.friend_creation_view_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.mSendMessage:
			Intent i = new Intent(getApplicationContext(),
					MessagingPlatform.class);
			startActivity(i);
			break;

		}
		return super.onOptionsItemSelected(item);
	}

}
