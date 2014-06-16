package com.writr.backend;

import java.util.ArrayList;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPConnection;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

public class AddContactsToRoster  {

	static String auserName;
	static String auserNumber;
	static ArrayList<String> theNames = new ArrayList<String>();
	static ArrayList<String> theNumbers = new ArrayList<String>();
	
	
	public static void createContactList(XMPPConnection connection, Context context ) {

		 Cursor cursor = context.getContentResolver().query(
		 ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
		 null, null);

		 while (cursor.moveToNext()) {
		 auserName = cursor
		 .getString(cursor
		 .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
		 theNames.add(auserName);
		 auserNumber = cursor
		 .getString(cursor
		 .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		 theNumbers.add(auserNumber.replace("-", "").trim());
		
		 }

		 for (int i = 0; i < theNumbers.size(); i++) {
		
		 Roster roster = connection.getRoster();
		 try {
		 roster.createEntry(theNumbers.get(i).toString().trim()
		 + "@candr.com", theNames.get(i).toString(), null);
		 } catch (Exception e) {
		 // TODO Auto-generated catch block
		 Log.e("error in adding contacts to roster", e.toString());
		 }
		 }
		Log.i("success", "welf");

	}

}
