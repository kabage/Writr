package com.writr.backend;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smackx.packet.VCard;
import org.jivesoftware.smackx.provider.VCardProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class loading {
	public static Bitmap bitmap;
	XMPPConnection connection;
	ImageView a;
	public static Boolean done = false;

	public void takeInImageView(final ImageView iv) {
		a = iv;
		loadingImage n = new loadingImage();
		n.execute();

	}

	public void retrieveVcard() {

		ConnectionConfiguration conf = new ConnectionConfiguration(
				ConnectionManager.HOST, ConnectionManager.PORT,
				ConnectionManager.SERVICE);
		connection = new XMPPConnection(conf);
		ProviderManager.getInstance().addIQProvider("vCard", "vcard-temp",
				new VCardProvider());

		try {
			connection.connect();

			Log.i("success", "successful connection to server");
		} catch (XMPPException e) {
			Log.e("unable to connect to the server", e.toString());
		}

		try {

			connection.login("admin", "xenomorph");
			gettingCard(connection);

		} catch (XMPPException ex) {
			Log.e("error logging in as user ", ex.toString());
		}

	}

	public Bitmap gettingCard(XMPPConnection connection) {
		VCard vcard = new VCard();
		try {

			// String x = connection.toString();
			// XMPPConnection connector = new XMPPConnection(x);

			vcard.load(connection, "admin@candr.com");
			Log.i("card loaded", vcard.getJabberId());

			bitmap = BitmapFactory.decodeByteArray(vcard.getAvatar(), 0,
					vcard.getAvatar().length);
			Log.i("image sizes", String.valueOf(bitmap.getDensity()));

		} catch (XMPPException e) {
			Log.e("error loading the card", e.toString());
		}
		return bitmap;

	}

	class loadingImage extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			retrieveVcard();

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			done = true;
			Log.i("data from image", String.valueOf(bitmap.getHeight()));
		//	a.setImageBitmap(bitmap);
			super.onPostExecute(result);

			
			// handler.sendEmptyMessage(0);
		}

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
		}

	}

}