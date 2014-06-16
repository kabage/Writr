package com.writr.backend;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.VCard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class UserVcard {

	public static Bitmap bitmap;

	/**
	 * @param connection
	 * @param userJid
	 * @return
	 */
	public static Bitmap gettingCard(XMPPConnection connection, String userJid) {
		VCard vcard = new VCard();
		try {

			vcard.load(connection, userJid);
			// Log.i("card loaded", vcard.getFirstName());

		} catch (XMPPException e) {
			Log.e("error loading the card", e.toString());
		}
		// an if statement to check for null value missing.
		bitmap = BitmapFactory.decodeByteArray(vcard.getAvatar(), 0,
				vcard.getAvatar().length);

		return bitmap;

	}
}
