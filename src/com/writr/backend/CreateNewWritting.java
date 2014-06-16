package com.writr.backend;

import java.util.ArrayList;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class CreateNewWritting {
	static XMPPConnection connect;

	public static void create(Context context, String creationTitle,
			String creationAuthor, String creationSummary,
			ArrayList<String> members) {

		connect = MaintainConnection.connection;

		createWritting(context, connect, creationTitle, creationAuthor,
				creationSummary, members);//

	}

	public static void createWritting(Context context,
			XMPPConnection connection, String creationTitle,
			String creationAuthor, String creationSummary,
			ArrayList<String> members) {
		MultiUserChat muc = new MultiUserChat(connection, creationTitle
				+ "@conference.candr.com");
		try {
			muc.create(creationTitle);
		} catch (XMPPException e1) {
			// TODO Auto-generated catch block
			Log.e("error in room creation", e1.toString());
		}

		Form form = null;
		try {
			form = muc.getConfigurationForm();
		} catch (XMPPException e1) {
			// TODO Auto-generated catch block
			Log.e("error getting room configuration form ", e1.toString());
		}
		Form submitForm = form.createAnswerForm();

		// submitForm.setAnswer("muc#roomconfig_roommembers", members);
		submitForm.setAnswer("muc#roomconfig_publicroom", true);
		submitForm.setAnswer("muc#roomconfig_persistentroom", true);
		submitForm.setAnswer(
				"muc#roomconfig_roomdesc",
				compilewrittingData(creationTitle, creationAuthor,
						creationSummary));
		try {
			muc.sendConfigurationForm(submitForm);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WrittingData.saveMywrittingLocally(context, creationTitle,
				creationTitle + "@conference.candr.com");
	}

	public static String compilewrittingData(String creationTitle,
			String creationAuthor, String creationSummary) {
		JSONObject roomData = new JSONObject();
		try {
			roomData.put("title", creationTitle);
			roomData.put("authorName", creationAuthor);
			roomData.put("summary", creationSummary);

		} catch (JSONException ex) {
			Log.e("error adding room data to json object", ex.toString());
		}
		return roomData.toString();
	}
}
