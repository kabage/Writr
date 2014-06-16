package com.writr.backend;

import java.util.ArrayList;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class ReceiveIncomingMessages {

	JSONArray participantIds;
	ArrayList<String> theWrittings = new ArrayList<String>();
	ArrayList<String> theWrittingBodies = new ArrayList<String>();

	public void wallChat(final XMPPConnection connect, MultiUserChat muc) {
		
		muc = new MultiUserChat(connect, "groupSelected");
		Log.i("value of groupSelected", "groupSelected");
		try {
			muc.join("USERNAME");
		} catch (XMPPException e) {
			Log.i("error joining ", e.toString());
		}
		muc.addMessageListener(new PacketListener() {

			@Override
			public void processPacket(Packet messagePacket) {
				Message post = (Message) messagePacket;
				 
				if (post.getSubject().equals("writingConfig")) {

					String message = post.getBody();
					try {
						JSONObject messageJson = new JSONObject(message);
						participantIds = messageJson
								.getJSONArray("participantIds");

						for (int i = 0; i < participantIds.length(); i++) {
							String participant = participantIds.getString(i);
							String participantJid = participant.concat("@"
									+ ConnectionManager.SERVICE);
							if (participantJid.equals(connect.getUser())) {

								String writtingName = (String) messageJson
										.get("writingName");

								theWrittings.add(writtingName);
								String writtingBody = (String) messageJson
										.get("writtingBody");
								theWrittingBodies.add(writtingBody);
							}

						}
					} catch (JSONException e) {
						Log.e("error parsing/receiving incoming json",
								e.toString());
					}
				}
			}
		});
	}

	public void saveNewWritting(Context context, String writingName) {

		SharedPreferences prefs = context.getSharedPreferences("writings", 0);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(writingName, "");
		editor.putString("writingChannel", "writingChanel");
		editor.commit();
	}

}
