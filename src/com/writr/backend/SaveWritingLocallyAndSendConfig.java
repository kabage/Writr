package com.writr.backend;

import java.util.ArrayList;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SaveWritingLocallyAndSendConfig {
	static String USERNAME;

	public static JSONObject saveWritingLocally(Context context,
			ArrayList<String> participants, String writingName) {

		JSONArray participantList = new JSONArray();
		for (int i = 0; i < participants.size(); i++) {
			participantList.put(participants.get(i));
		}

		JSONObject writingDetails = new JSONObject();
		try {
			writingDetails.put("theWriting", writingName);
			writingDetails.put("participantList", participantList);

		} catch (JSONException e) {
			Log.e("error creating writing to shared preferences ", e.toString());
		}

		SharedPreferences prefs = context.getSharedPreferences("writings", 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(writingName, writingDetails.toString());
		editor.commit();
		return writingDetails;
	}

	public static void sendWritingConfiguration(Context context,
			XMPPConnection connect, JSONObject writingDetails) {

		SharedPreferences prefs = context.getSharedPreferences(
				"RegistrationState", Context.MODE_PRIVATE);
		USERNAME = prefs.getString("USERNAME", "");
		Message msg = new Message(USERNAME + "@"
				+ ConnectionManager.CHAT_SERVICE, Message.Type.groupchat);

		msg.setSubject("writingConfig");
		msg.setBody(writingDetails.toString());
		MultiUserChat muc = new MultiUserChat(connect, USERNAME + "@"
				+ ConnectionManager.CHAT_SERVICE);
		
		try {
			muc.sendMessage(msg);
		} catch (XMPPException e) {
			Log.e("error sending writingConfiguration", e.toString());
		}
	}

}
