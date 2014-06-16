package com.writr;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.writr.backend.GetCreationContent;
import com.writr.backend.InfoOnWritting;

public class EditableCreationView extends SherlockActivity {

	TextView tvCreationTitle, tvCreationAuthor, tvCreation;
	String id, title, authorName, summary, imageUrl;
	ArrayList<String> creation = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.editable_creation_view);
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
		tvCreation.setText(creation.toString());
	}

	private void setUp() {
		// TODO Auto-generated method stub
		tvCreation = (TextView) findViewById(R.id.tvCreationEditable);
		tvCreationTitle = (TextView) findViewById(R.id.tvCreationTitleEditable);
		tvCreationAuthor = (TextView) findViewById(R.id.tvCreationAuthorEditable);
	}

	private void getBundle() {
		// TODO Auto-generated method stub
		Bundle b = getIntent().getExtras();

		title = b.getString("title");
		JSONObject details = InfoOnWritting.info(title);
		try {
			authorName = details.getString("authorName");
			summary = details.getString("summary");
		} catch (JSONException e) {
			Log.e("error getting creation details editablecreationView",
					e.toString());
		}
		creation = GetCreationContent.getContent(title);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.editable_creation_view_menu,
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
		case R.id.mEditCreation:
			// Use ID to get the appropriate data to bundle to the editor
			Intent i = new Intent(getApplicationContext(), ContentEditor.class);
			Bundle b = new Bundle();
			b.putString("id", id);
			b.putString("title", title);
			b.putString("authorName", authorName);
			b.putString("summary", summary);
			b.putStringArrayList("content", creation);
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
