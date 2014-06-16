package com.writr.backend;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NewCreationListener extends Service {
	XMPPConnection connection;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub

		return null;

	}

	@Override
	public void onCreate() {

		MultiUserChat.addInvitationListener(connection,
				new InvitationListener() {

					@Override
					public void invitationReceived(Connection conn,
							String room, String inviter, String reason,
							String password, Message arg5) {
						
					}
				});
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
