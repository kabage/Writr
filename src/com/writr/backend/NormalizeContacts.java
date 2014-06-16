package com.writr.backend;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

public class NormalizeContacts {

	public static void readsomestuff(Context context) {

		ContextWrapper contextWrapper = new ContextWrapper(context);

		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection = new String[] {
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER };

		Cursor people = contextWrapper.getContentResolver().query(uri,
				projection, null, null, null);

		int indexName = people
				.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		int indexNumber = people
				.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

		people.moveToFirst();
		do {
			String name = people.getString(indexName);
			String number = people.getString(indexNumber);
			Log.i("the contacts read", name + number);
			// Do work...
		} while (people.moveToNext());
	}
}
