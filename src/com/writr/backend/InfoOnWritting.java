package com.writr.backend;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.RoomInfo;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class InfoOnWritting {
	private static JSONObject details;

	public static JSONObject info(String creationTitle) {
		XMPPConnection connection = MaintainConnection.connection;
		String creationJid = creationTitle + "@conference.candr.com";
		try {
			RoomInfo info = MultiUserChat.getRoomInfo(connection, creationJid);
			try {
				details = new JSONObject(info.getDescription());
			} catch (JSONException e) {
				Log.e("an error occcured creating json object from description",
						e.toString());
			}

		} catch (XMPPException e) {
			Log.e("an error occured retrieving info on writting", e.toString());

		}
		return details;
	}
}
