package com.writr;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class AboutCreation extends SherlockActivity {

	String title, authorName, summary, imageUrl;
	TextView tvCreationTitle, tvAuthorName, tvCreationSummary;
	ImageView ivAuthorImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_creation);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setUp();
		getBundle();
		setData();
	}

	private void setUp() {
		// TODO Auto-generated method stub
		tvCreationTitle = (TextView) findViewById(R.id.tvCreationTitle);
		tvAuthorName = (TextView) findViewById(R.id.tvCreationAuthor);
		tvCreationSummary = (TextView) findViewById(R.id.tvCreationSummary);
		ivAuthorImage = (ImageView) findViewById(R.id.ivAuthorImage);
		Toast.makeText(this, title, Toast.LENGTH_LONG).show();
	}

	private void setData() {
		// TODO Auto-generated method stub
		tvCreationTitle.setText(title);
		tvAuthorName.setText("authorName");
		tvCreationSummary.setText("summary");
		// new LoadImage().execute();
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
		title = b.getString("title");
		Log.i("the title has been successfuly received", title);
		authorName = b.getString("authorName");
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

}
