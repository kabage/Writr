package com.writr.backend;

import java.util.ArrayList;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RetrievePublished {
	public static ArrayList<JSONObject> listOfPubs = new ArrayList<JSONObject>();
	static XMPPConnection connect;
	static JSONObject pubJson;

	public static ArrayList<JSONObject> retrive() {
		connect = MaintainConnection.connection;
		MultiUserChat muc = new MultiUserChat(connect,
				"publish@conference.candr.com");
		try {
			muc.join(connect.getUser());
		} catch (XMPPException e) {
			Log.e("error ocurred joining publish ", e.toString());
		}
		muc.addMessageListener(new PacketListener() {

			@Override
			public void processPacket(Packet pack) {
				Message msg = (Message) pack;
				String publicationString = msg.getBody();
				Log.i("message from room", publicationString);
				if (publicationString != null) {
					listOfPubs.add(getJsonFromString(publicationString));
				}

			}
		});
		return listOfPubs;
	}

	public static JSONObject getJsonFromString(String pubText) {

		try {
			pubJson = new JSONObject(pubText);
		} catch (JSONException e) {
			Log.e("error converting txt to json", e.toString());
		}
		return pubJson;
	}
}
