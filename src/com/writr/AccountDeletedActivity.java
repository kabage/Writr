package com.writr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;

public class AccountDeletedActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_deleted_activity);

		deleteUser();
	}

	private void deleteUser() {
		// TODO Auto-generated method stub
		deleteUserInfo();
		new Thread(new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2500);
					System.runFinalizersOnExit(true);
					SharedPreferences sp = getSharedPreferences(
							getPackageName(), MODE_PRIVATE);
					sp.edit().putBoolean("registered", false).commit();
					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_HOME);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void deleteUserInfo() {
		// TODO Auto-generated method stub
		// Delete the user Informatiom from the database
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
