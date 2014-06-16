package com.writr.backend;

import java.util.ArrayList;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.MultiUserChat;

import android.util.Log;

public class GetCreationContent {
	static ArrayList<String> messageThread = new ArrayList<String>();

	public static ArrayList<String> getContent(String creationTitle) {
		XMPPConnection connection = MaintainConnection.connection;
		MultiUserChat muc = new MultiUserChat(connection, creationTitle
				+ "@conference.candr.com");

		try {
			muc.join(connection.getUser());
			muc.addMessageListener(new PacketListener() {

				@Override
				public void processPacket(Packet chatPacket) {

					Message message = (Message) chatPacket;
					messageThread.add(message.getBody());
				}
			});
		} catch (XMPPException e) {
			Log.e("error joining creation", e.toString());
		}
		return messageThread;
	}
}
