package com.writr;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.writr.backend.CreateNewWritting;

public class CreateNew extends SherlockActivity {

	EditText etCreationTitle, etCreationSummary, etCreationAuthor;
	Spinner spCreationCategory;
	String id, creationTitle, creationSummary, creationAuthor,
			creationCategory;
	ArrayList<String> creationCategories;
	Button bCreateNew;
	ArrayList<String> friends = new ArrayList<String>();
	Boolean create = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_new);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setUp();
	}

	private void getData() {
		// TODO Auto-generated method stub
		creationTitle = etCreationTitle.getText().toString().trim();
		creationSummary = etCreationSummary.getText().toString().trim();
		creationAuthor = etCreationAuthor.getText().toString().trim();
		creationCategory = spCreationCategory.getSelectedItem().toString();

		if (creationTitle.length() < 3) {
			create = false;
			etCreationTitle
					.setError("Please enter a title that has at least 3 characters");
		} else {
			create = true;
			etCreationTitle.setError(null);
		}

		if (creationSummary.length() < 15) {
			create = false;
			etCreationSummary
					.setError("Please enter a summary that has at least 15 characters");
		} else {
			create = true;
			etCreationSummary.setError(null);
		}

		if (creationAuthor.length() < 3) {
			create = false;
			etCreationAuthor
					.setError("Please enter an author name that has at least 3 characters");
		} else {
			create = true;
			etCreationAuthor.setError(null);
		}

	}

	private void setUp() {
		// TODO Auto-generated method stub

		etCreationTitle = (EditText) findViewById(R.id.etCreationTitle);
		etCreationSummary = (EditText) findViewById(R.id.etCreationSummary);
		etCreationAuthor = (EditText) findViewById(R.id.etCreationAuthor);
		spCreationCategory = (Spinner) findViewById(R.id.spCreationCategory);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.creationCategories, R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
		spCreationCategory.setAdapter(adapter);
		bCreateNew = (Button) findViewById(R.id.bCreateNew);
		bCreateNew.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getData();
				if (create == true) {
					createNew();
					Intent i = new Intent(getApplicationContext(),
							ContentEditor.class);
					Bundle b = new Bundle();
					b.putString("id", id);
					b.putString("title", creationTitle);
					b.putString("authorName", creationAuthor);
					b.putString("summary", creationSummary);
					b.putString("content", "YOLO");
					b.putString("imageUrl",
							"http://www.red967fm.com/wp-content/uploads/2014/02/profileholder-150x150.gif");
					i.putExtras(b);
					startActivity(i);
					startActivity(i);
				}
			}
		});
	}

	private void createNew() {
		// TODO Auto-generated method stub
		// Add the data to the database
		friends.add("billy@candr.com");
		friends.add("silly@candr.com");
		CreateNewWritting.create(this, creationTitle, creationAuthor,
				creationSummary, friends);
	
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
