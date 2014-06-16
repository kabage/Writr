package com.writr.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class WrittingData {
	static ArrayList<String> allMyCreationNames = new ArrayList<String>();
	static ArrayList<String> allMyCreationJids = new ArrayList<String>();
	static ArrayList<String> allFavoritesNames = new ArrayList<String>();
	static ArrayList<String> allFavoritesJids = new ArrayList<String>();

	public static void saveMywrittingLocally(Context context,
			String creationName, String creationJid) {

		SharedPreferences prefs = context.getSharedPreferences("MY_WRITTINGS",
				Context.MODE_PRIVATE);
		Editor mEditor = prefs.edit();
		mEditor.putString(creationName, creationJid);
		mEditor.commit();
		Log.i("successfuly saved my writting", "success");

	}

	public static ArrayList<String> myWrittingDetails(Context context) {

		SharedPreferences prefs = context.getSharedPreferences("MY_WRITTINGS",
				Context.MODE_PRIVATE);

		@SuppressWarnings("unchecked")
		Map<String, String> log = (Map<String, String>) prefs.getAll();
		Set<String> keys = log.keySet();
		allMyCreationNames.addAll(keys);
		Collection<String> values = log.values();
		allMyCreationJids.addAll(values);
		return allMyCreationNames;
	}

	public static void createFavorite(Context context, String creationName,
			String creationJid) {
		SharedPreferences prefs = context.getSharedPreferences("FAVORITES",
				Context.MODE_PRIVATE);
		Editor mEditor = prefs.edit();
		mEditor.putString(creationName, creationJid);
		mEditor.commit();
		Log.i("successfuly stored up registration details", "success");

	}

	public static void retrieveFavorites(Context context) {
		SharedPreferences prefs = context.getSharedPreferences("FAVORITES",
				Context.MODE_PRIVATE);

		@SuppressWarnings("unchecked")
		Map<String, String> log = (Map<String, String>) prefs.getAll();
		Set<String> keys = log.keySet();
		allFavoritesNames.addAll(keys);
		Collection<String> values = log.values();
		allFavoritesJids.addAll(values);
	}

}
