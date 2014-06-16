package com.writr;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class FriendProfile extends SherlockActivity {

	String id, friendName, imageUrl;
	TextView tvFriendName, tvFriendStatus;
	ImageView ivFriendImage;
	Button bChatWithFriend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_profile);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setUp();
		getBundle();
		setData();
	}

	private void setUp() {
		// TODO Auto-generated method stub
		tvFriendName = (TextView) findViewById(R.id.tvFriendName);
		ivFriendImage = (ImageView) findViewById(R.id.ivFriendImage);
		bChatWithFriend = (Button) findViewById(R.id.bChatWithFriend);
		bChatWithFriend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						MessagingPlatform.class);
				Bundle b = new Bundle();
				b.putString("id", id);
				b.putString("friendName", friendName);
				b.putString("profilePicture", imageUrl);
				i.putExtras(b);
				startActivity(i);
			}
		});
	}

	private void setData() {
		// TODO Auto-generated method stub
		tvFriendName.setText(friendName);
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
			ivFriendImage.setImageBitmap(bmp);
		}

	}

	private void getBundle() {
		// TODO Auto-generated method stub
		Bundle b = getIntent().getExtras();
		id = b.getString("id");
		friendName = b.getString("friendName");
		imageUrl = b.getString("profilePicture");
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
