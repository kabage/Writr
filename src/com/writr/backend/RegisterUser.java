package com.writr.backend;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackAndroid;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smackx.PrivateDataManager;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.packet.SharedGroupsInfo;
import org.jivesoftware.smackx.packet.VCard;
import org.jivesoftware.smackx.provider.DataFormProvider;
import org.jivesoftware.smackx.provider.DiscoverInfoProvider;
import org.jivesoftware.smackx.provider.DiscoverItemsProvider;
import org.jivesoftware.smackx.provider.MUCAdminProvider;
import org.jivesoftware.smackx.provider.MUCOwnerProvider;
import org.jivesoftware.smackx.provider.MUCUserProvider;
import org.jivesoftware.smackx.provider.RosterExchangeProvider;
import org.jivesoftware.smackx.provider.VCardProvider;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

public class RegisterUser {

	static Thread t;
	public static XMPPConnection connection;
	public static String auserName, auserNumber;

	ArrayList<String> theNames = new ArrayList<String>();
	ArrayList<String> theNumbers = new ArrayList<String>();
	static Map<String, String> newRegAttrMap = new HashMap<String, String>();
	static MultiUserChat muc;

	public static void register(final Context context, final String USERNAME,
			final String PHONE_NUMBER, final String PASSWORD, final Bitmap photo) {

		t = new Thread(new Runnable() {

			@Override
			public void run() {
				Looper.prepare();
				SmackAndroid.init(context);
				System.setProperty("smack.debugEnabled", "true");
				SASLAuthentication.supportSASLMechanism("PLAIN", 0);
				XMPPConnection.DEBUG_ENABLED = true;
				ConnectionConfiguration conf = new ConnectionConfiguration(
						ConnectionManager.HOST, ConnectionManager.PORT,
						ConnectionManager.SERVICE);

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
					conf.setTruststoreType("AndroidCAStore");
					conf.setTruststorePassword(null);
					conf.setTruststorePath(null);
				} else {
					conf.setTruststoreType("BKS");
					String path = System
							.getProperty("javax.net.ssl.trustStore");
					if (path == null)
						path = System.getProperty("java.home") + File.separator
								+ "etc" + File.separator + "security"
								+ File.separator + "cacerts.bks";
					conf.setTruststorePath(path);
				}
				connection = new XMPPConnection(conf);

				ProviderManager.getInstance().addIQProvider("vCard",
						"vcard-temp", new VCardProvider()); // get point and
															// last tag
				ProviderManager pm = ProviderManager.getInstance();
				pm.addIQProvider("query",
						"http://jabber.org/protocol/disco#info",
						new DiscoverInfoProvider());
				pm.addExtensionProvider("x", "jabber:x:data",
						new DataFormProvider());
				pm.addExtensionProvider("x",
						"http://jabber.org/protocol/muc#user",
						new MUCUserProvider());
				pm.addIQProvider("query",
						"http://jabber.org/protocol/muc#admin",
						new MUCAdminProvider());
				pm.addIQProvider("query",
						"http://jabber.org/protocol/muc#owner",
						new MUCOwnerProvider());
				pm.addIQProvider("query",
						"http://jabber.org/protocol/disco#items",
						new DiscoverItemsProvider());
				pm.addIQProvider("query",
						"http://jabber.org/protocol/muc#rooms",
						new DiscoverItemsProvider());
				pm.addExtensionProvider("x", "jabber:x:roster",
						new RosterExchangeProvider());
				pm.addIQProvider("query", "jabber:iq:private",
						new PrivateDataManager.PrivateDataIQProvider());
				pm.addIQProvider("sharedgroup",
						"http://www.jivesoftware.org/protocol/sharedgroup",
						new SharedGroupsInfo.Provider());

				try {
					connection.connect();
					Class.forName("org.jivesoftware.smack.ReconnectionManager");
					registerThePerson(connection, USERNAME, PHONE_NUMBER,
							PASSWORD);
					Log.i("usernmae and password are", PHONE_NUMBER + PASSWORD);
					if (connection.isAuthenticated() == false) {
						connection.login(PHONE_NUMBER, "password");
						createVcard(photo, connection, "usernamae");
					} else {
						createVcard(photo, connection, "usernamae");
					}
					connection.disconnect();

				} catch (Exception ex) {
					connection = null;
					Log.e("error in registration or vcard creation",
							ex.toString());
				}

			}
		});
		t.start();

	}

	public static void registerThePerson(XMPPConnection connection,
			String USERNAME, String PHONE_NUMBER, String PASSWORD) {

		newRegAttrMap.put("name", USERNAME);

		newRegAttrMap.put("email", null);
		try {
			connection.getAccountManager().createAccount(PHONE_NUMBER,
					PASSWORD, newRegAttrMap);
			if (connection.isAuthenticated() == false) {
				connection.login(PHONE_NUMBER, "password"); // hardcoded
															// password,
															// messed up
															// intents.fix later
			}
		} catch (XMPPException e) {
			Log.e("error in registration", e.toString());
			e.printStackTrace();
		}

	}

	public static void createVcard(Bitmap photo, XMPPConnection connection,
			String username) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] photoData = stream.toByteArray();
		VCard vCard = new VCard();
		vCard.setFirstName(username);
		vCard.setAvatar(photoData);
		try {
			vCard.save(connection);
		} catch (XMPPException e) {

			Log.e("error saving the users's vcard", e.toString());
		}

	}

	public static void disconnnectTheSession() {

		connection.disconnect();
	}

}