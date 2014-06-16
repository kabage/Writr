package com.writr.backend;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.json.JSONObject;

import android.util.Log;

public class PublishCreation {
	static XMPPConnection connect;

	public static void publish(JSONObject creationToPublish) {

		connect = MaintainConnection.connection;
		MultiUserChat muc = new MultiUserChat(connect,
				"publish@conference.candr.com");
		try {
			muc.join(connect.getUser());
		} catch (XMPPException e) {
			Log.e("error joining publishing room", e.toString());
		}
		try {
			muc.sendMessage(creationToPublish.toString());
		} catch (XMPPException e) {
			Log.e("error publishing to the room", e.toString());
		}
		muc.leave();
	}
}
