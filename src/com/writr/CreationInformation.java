package com.writr;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class CreationInformation extends SherlockActivity implements
		OnClickListener {

	String id, title, author, summary, imageUrl;
	TextView tvTitle, tvAuthorName, tvCreationSummary;
	ImageView ivAuthorImage;
	Intent i;
	Bundle b;
	AlertDialog.Builder editalert;
	AlertDialog dialog;
	EditText input;
	LinearLayout.LayoutParams lp;
	Boolean edit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creation_information);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast,
				(ViewGroup) findViewById(R.id.toast_layout_root));
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText("Tap on any field to edit it");
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();

		getBundle();
		setUp();
		setData();
	}

	private void setUp() {
		// TODO Auto-generated method stub
		tvTitle = (TextView) findViewById(R.id.tvCreationTitle);
		tvAuthorName = (TextView) findViewById(R.id.tvCreationAuthor);
		tvCreationSummary = (TextView) findViewById(R.id.tvCreationSummary);
		ivAuthorImage = (ImageView) findViewById(R.id.ivAuthorImage);

		tvTitle.setOnClickListener(this);
		tvAuthorName.setOnClickListener(this);
		tvCreationSummary.setOnClickListener(this);
	}

	private void setData() {
		// TODO Auto-generated method stub
		tvTitle.setText(title);
		tvAuthorName.setText(author);
		tvCreationSummary.setText(summary);
		//new LoadImage().execute();
	}

	private class LoadImage extends AsyncTask<Void, Void, String> {
		InputStream input;
		Bitmap bmp;

		@Override
		protected String doInBackground(Void... params) {
			try {
				URL url = new URL(imageUrl);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setDoInput(true);
				input = connection.getInputStream();
				bmp = BitmapFactory.decodeStream(input);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			ivAuthorImage.setImageBitmap(bmp);
		}
	}

	private void getBundle() {
		// TODO Auto-generated method stub
		Bundle b = getIntent().getExtras();
		id = b.getString("id");
		title = b.getString("title");
		author = b.getString("author");
		summary = b.getString("summary");
		imageUrl = b.getString("imageUrl");
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

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tvCreationTitle:
			editalert = new AlertDialog.Builder(this);
			editalert.setTitle("Edit title");
			editalert.setMessage("Enter a new title");
			input = new EditText(this);
			lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.FILL_PARENT);
			input.setLayoutParams(lp);
			editalert.setView(input);
			editalert.setPositiveButton("Done",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int arg2) {
						}
					});
			editalert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int arg2) {
							dialog.dismiss();
						}
					});
			dialog = editalert.create();
			dialog.show();
			dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(
					new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Boolean close = false;
							String newTitleString = input.getText().toString()
									.trim();
							if (newTitleString.length() < 3) {
								edit = false;
								input.setError("Please enter a title that has at least 3 characters");
							} else {
								edit = true;
								input.setError(null);
							}

							if (edit == true) {
								close = true;
								// Replace title for new title using the id
								// given

								LayoutInflater inflater = getLayoutInflater();
								View layout = inflater
										.inflate(
												R.layout.toast,
												(ViewGroup) findViewById(R.id.toast_layout_root));
								TextView text = (TextView) layout
										.findViewById(R.id.text);
								text.setText("Title changed to '"
										+ newTitleString + "'");
								Toast toast = new Toast(getApplicationContext());
								toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
								toast.setDuration(Toast.LENGTH_LONG);
								toast.setView(layout);
								toast.show();
							} else {
								close = false;
							}

							if (close == true) {
								dialog.dismiss();
							}
						}
					});
			break;
		case R.id.tvCreationAuthor:
			editalert = new AlertDialog.Builder(this);
			editalert.setTitle("Edit author");
			editalert.setMessage("Enter a new author name");
			input = new EditText(this);
			lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.FILL_PARENT);
			input.setLayoutParams(lp);
			editalert.setView(input);
			editalert.setPositiveButton("Done",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int arg2) {
						}
					});
			editalert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int arg2) {
							dialog.dismiss();
						}
					});
			dialog = editalert.create();
			dialog.show();
			dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(
					new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Boolean close = false;
							String newAuthorString = input.getText().toString()
									.trim();
							if (newAuthorString.length() < 3) {
								edit = false;
								input.setError("Please enter an author name that has at least 3 characters");
							} else {
								edit = true;
								input.setError(null);
							}

							if (edit == true) {
								close = true;
								// Replace title for new title using the id
								// given

								LayoutInflater inflater = getLayoutInflater();
								View layout = inflater
										.inflate(
												R.layout.toast,
												(ViewGroup) findViewById(R.id.toast_layout_root));
								TextView text = (TextView) layout
										.findViewById(R.id.text);
								text.setText("Author changed to '"
										+ newAuthorString + "'");
								Toast toast = new Toast(getApplicationContext());
								toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
								toast.setDuration(Toast.LENGTH_LONG);
								toast.setView(layout);
								toast.show();
							} else {
								close = false;
							}

							if (close == true) {
								dialog.dismiss();
							}
						}
					});
			break;
		case R.id.tvCreationSummary:
			editalert = new AlertDialog.Builder(this);
			editalert.setTitle("Edit summary");
			editalert.setMessage("Enter a new summary");
			input = new EditText(this);
			lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.FILL_PARENT);
			input.setLayoutParams(lp);
			editalert.setView(input);
			editalert.setPositiveButton("Done",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int arg2) {
						}
					});
			editalert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int arg2) {
							dialog.dismiss();
						}
					});
			dialog = editalert.create();
			dialog.show();
			dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(
					new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Boolean close = false;
							String newSummaryString = input.getText()
									.toString().trim();
							if (newSummaryString.length() < 15) {
								edit = false;
								input.setError("Please enter a summary that has at least 15 characters");
							} else {
								edit = true;
								input.setError(null);
							}

							if (edit == true) {
								close = true;
								// Replace title for new title using the id
								// given

								LayoutInflater inflater = getLayoutInflater();
								View layout = inflater
										.inflate(
												R.layout.toast,
												(ViewGroup) findViewById(R.id.toast_layout_root));
								TextView text = (TextView) layout
										.findViewById(R.id.text);
								text.setText("Summary changed");
								Toast toast = new Toast(getApplicationContext());
								toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
								toast.setDuration(Toast.LENGTH_LONG);
								toast.setView(layout);
								toast.show();
							} else {
								close = false;
							}

							if (close == true) {
								dialog.dismiss();
							}
						}
					});
			break;
		}
	}

}
