package com.writr.backend;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class CheckIfRegistered {

	static String regStatus;

	public static boolean checkRegistrationState(Context context) {

		try {
			SharedPreferences prefs = context.getSharedPreferences(
					"RegistrationState", Context.MODE_PRIVATE);
			regStatus = prefs.getString("regStatus", "");
		} catch (Exception e) {
			Log.d("no file", e.toString());
		}
		if (regStatus != "registered") {

			return false;
		} else {
			return true;
		}

	}

}
