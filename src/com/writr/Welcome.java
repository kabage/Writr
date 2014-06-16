package com.writr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;

public class Welcome extends SherlockActivity {

	Button bWritrLogo, bContinue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.welcome);
		getSupportActionBar().hide();

		checkUserRegistration();
		setUp();

	}

	private void checkUserRegistration() {
		// TODO Auto-generated method stub
		SharedPreferences sp = this.getSharedPreferences(getPackageName(),
				MODE_PRIVATE);
		Boolean registered = sp.getBoolean("registered", false);
		if (registered == true) {
			Intent i = new Intent(getApplicationContext(), Dashboard.class);
			startActivity(i);
			finish();
		}

	}

	private void setUp() {
		// TODO Auto-generated method stub
		bWritrLogo = (Button) findViewById(R.id.bWritrLogo);
		bWritrLogo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(Welcome.this)
						.setTitle("Welcome")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setMessage(
								"Blame the monkey species for any errors...")
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).show();
			}
		});
		bContinue = (Button) findViewById(R.id.bContinue);
		bContinue.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						UserRegistration.class);
				startActivity(i);
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
