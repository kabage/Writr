package com.writr.backend;

import java.util.ArrayList;
import java.util.Collection;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;

import android.graphics.Bitmap;
import android.util.Log;

public class RosterRetrieval {

	static XMPPConnection connect;
	public static ArrayList<String> userFriends = new ArrayList<String>();
	public static ArrayList<String> names = new ArrayList<String>();
	public static ArrayList<Bitmap> pics = new ArrayList<Bitmap>();
	public static ArrayList<String> jids = new ArrayList<String>();

	public static ArrayList<String> retrieve() {

		connect = MaintainConnection.connection;
		userFriends = retrieveRosterOfRegisteredUsers(connect);

		return userFriends;

	}

	public static ArrayList<String> retrieveRosterOfRegisteredUsers(
			XMPPConnection connect) {
		if (names.isEmpty()) {
			Roster roster = connect.getRoster();
			Collection<RosterEntry> entries = roster.getEntries();
			for (RosterEntry entry : entries) {
				names.add(entry.getName());
				jids.add(entry.getUser());
				Log.i("the not unregistered", entry.getName() + entry.getUser());
			}
		}
		return names;
	}

	public static ArrayList<Bitmap> pictures() {
		if (pics.isEmpty()) {
			for (int i = 0; i < jids.size(); i++) {
				Bitmap bm = UserVcard.gettingCard(connect, jids.get(i));
				pics.add(bm);

			}
			Log.i("number of pics", String.valueOf(pics.size()));
		}
		return pics;

	}

	public void connectionTermination() {
		connect.disconnect();
	}
}
