package com.writr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

public class FavouritesCreationView extends SherlockActivity {

	TextView tvCreationTitle, tvCreationAuthor, tvCreation;
	String id, title, creation, authorName, summary, imageUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.non_editable_creation_view);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.overlay_action_bar));

		setUp();
		getBundle();
		setData();
	}

	private void setData() {
		// TODO Auto-generated method stub
		tvCreationTitle.setText(title);
		tvCreationAuthor.setText(authorName);
		tvCreation.setText(creation);
	}

	private void setUp() {
		// TODO Auto-generated method stub
		tvCreation = (TextView) findViewById(R.id.tvCreationNonEditable);
		tvCreationTitle = (TextView) findViewById(R.id.tvCreationTitleNonEditable);
		tvCreationAuthor = (TextView) findViewById(R.id.tvCreationAuthorNonEditable);
	}

	private void getBundle() {
		// TODO Auto-generated method stub
		Bundle b = getIntent().getExtras();
		id = b.getString("id");
		title = b.getString("title");
		creation = b.getString("creation");
		authorName = b.getString("authorName");
		summary = b.getString("summary");
		imageUrl = b.getString("imageUrl");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.favourites_creation_view_menu,
				menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.mAboutCreation:
			// Use ID to get the appropriate data to bundle to the editor
			Intent i = new Intent(getApplicationContext(), AboutCreation.class);
			Bundle b = new Bundle();
			b.putString("title", title);
			b.putString("authorName", authorName);
			b.putString("summary", summary);
			b.putString("imageUrl", imageUrl);
			i.putExtras(b);
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
