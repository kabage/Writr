package com.writr;

import java.util.ArrayList;
import java.util.Random;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;

public class MessagingPlatform extends SherlockListActivity {

	ArrayList<Message> messages;
	CreationContentAdapter adapter;
	EditText text;
	static Random rand = new Random();
	static String sender;
	Intent i;
	String id, friendName, profilePicture;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_editor_layout);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getBundle();
		setUp();
		populateChatView();

	}

	private void getBundle() {
		// TODO Auto-generated method stub
		Bundle b = new Bundle();
		id = b.getString("id");
		friendName = b.getString("friendName");
		profilePicture = b.getString("profilePicture");
	}

	private void setUp() {
		// TODO Auto-generated method stub
		text = (EditText) this.findViewById(R.id.text);
	}

	private void populateChatView() {
		// TODO Auto-generated method stub
		sender = Utility.sender[rand.nextInt(Utility.sender.length - 1)];
		this.setTitle(sender);
		messages = new ArrayList<Message>();

		messages.add(new Message("That awkward moment...", false));
		messages.add(new Message("When the whole class fails a test", true));
		messages.add(new Message(
				"When you start a slow clap and no one else joins in", false));
		messages.add(new Message(
				"When you say something funny, then someone else says it louder and gets all the credit",
				true));
		messages.add(new Message(
				"When the only thing you know in a test is the date and your name",
				true));
		messages.add(new Message(
				"When you don't feel your phone in your pocket", false));
		messages.add(new Message("When you smile at the bitch who hates you..",
				true));

		adapter = new CreationContentAdapter(this, messages);
		setListAdapter(adapter);
	}

	public void sendMessage(View v) {
		String newMessage = text.getText().toString().trim();
		if (newMessage.length() > 0) {
			text.setText("");
			addNewMessage(new Message(newMessage, true));
			new SendMessage().execute();
		}
	}

	private class SendMessage extends AsyncTask<Void, String, String> {
		@Override
		protected String doInBackground(Void... params) {
			try {
				Thread.sleep(2000); // simulate a network call
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.publishProgress(String.format("%s is typing...", sender));
			try {
				Thread.sleep(2000); // simulate a network call
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.publishProgress(String
					.format("%s has entered text...", sender));
			try {
				Thread.sleep(3000);// simulate a network call
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return Utility.messages[rand.nextInt(Utility.messages.length - 1)];

		}

		@Override
		public void onProgressUpdate(String... v) {

			if (messages.get(messages.size() - 1).isStatusMessage)// check
																	// wether we
																	// have
																	// already
																	// added a
																	// status
																	// message
			{
				messages.get(messages.size() - 1).setMessage(v[0]); // update
																	// the
																	// status
																	// for that
				adapter.notifyDataSetChanged();
				getListView().setSelection(messages.size() - 1);
			} else {
				addNewMessage(new Message(true, v[0])); // add new message, if
														// there is no existing
														// status message
			}
		}

		@Override
		protected void onPostExecute(String text) {
			if (messages.get(messages.size() - 1).isStatusMessage)// check if
																	// there is
																	// any
																	// status
																	// message,
																	// now
																	// remove
																	// it.
			{
				messages.remove(messages.size() - 1);
			}

			addNewMessage(new Message(text, false)); // add the orignal message
														// from server.
		}

	}

	void addNewMessage(Message m) {
		messages.add(m);
		adapter.notifyDataSetChanged();
		getListView().setSelection(messages.size() - 1);
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

}