package com.writr.backend;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.RoomInfo;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class GetDataOnWritting {
	String author;
	String title;
	String summary;
	static JSONObject descriptionObject;
	static String infoString;

	public static JSONObject getData(XMPPConnection connect, String creationName) {

		try {
			RoomInfo info = MultiUserChat.getRoomInfo(connect, creationName
					+ "@conference.candr.com");
			infoString = info.getDescription();
		} catch (XMPPException e) {
			Log.e("an error occured getting room information", e.toString());
		}
		Log.i("success getting the room info", "success");

		try {
			descriptionObject = new JSONObject(infoString);
		} catch (JSONException e) {

			Log.e("error trying to get a json from string ", e.toString());
		}
		Log.i("success getting json info", "success");
		return descriptionObject;
	}
}
