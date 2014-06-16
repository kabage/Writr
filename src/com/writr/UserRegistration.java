package com.writr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Window;
import com.writr.backend.SaveOrRetrieveLoginDetails;

public class UserRegistration extends SherlockActivity {

	EditText etUsername, etPassword, etPhoneNumber;
	String username, password, phoneNumber;
	Button bRegisterUser;
	CheckBox cbViewPassword;
	boolean valid = false;
	boolean haveConnectedWifi = false;
	boolean haveConnectedMobile = false;
	Context context = UserRegistration.this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.user_registration);
		setSupportProgressBarIndeterminateVisibility(false);

		setUp();
	}

	private void getData() {
		// TODO Auto-generated method stub
		username = etUsername.getText().toString().trim();
		password = etPassword.getText().toString().trim();
		phoneNumber = etPhoneNumber.getText().toString().trim();

		if (username.length() < 3) {
			etUsername
					.setError("Please enter a username that has at least 3 characters");
			valid = false;
		} else {
			etUsername.setError(null);
			valid = true;
		}

		if (password.length() < 6) {
			etPassword
					.setError("Please enter a password that has at least 6 characters");
			valid = false;
		} else {
			etPassword.setError(null);
			valid = true;
		}

		if (phoneNumber.startsWith("+") && phoneNumber.length() > 10) {
			etPhoneNumber.setError(null);
			valid = true;
		} else {
			etPhoneNumber
					.setError("Please enter a valid phone number,  start with your country code");
			valid = false;
		}
	}

	private boolean checkNetworkConnection() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

	private void setUp() {
		// TODO Auto-generated method stub
		etUsername = (EditText) findViewById(R.id.etUsername);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
		cbViewPassword = (CheckBox) findViewById(R.id.cbViewPassword);
		cbViewPassword
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked == true) {
							etPassword
									.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
						} else {
							etPassword.setInputType(129);
						}
					}
				});
		bRegisterUser = (Button) findViewById(R.id.bRegisterUser);
		bRegisterUser.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getData();
				Log.i("Data from the views", username + password);
				checkNetworkConnection();
				if (haveConnectedMobile == true || haveConnectedWifi == true) {
					if (valid == true) {
						new AlertDialog.Builder(UserRegistration.this)
								.setTitle("Confirmation")
								.setIcon(android.R.drawable.ic_dialog_alert)
								.setMessage(
										"Writr will now send a confirmation text message which may be charged by your service provider\n\nIf you have installed a messaging app other than the default Android messaging app, we may have difficulties confirming your number and suggest that you temporarily uninstall the app.")
								.setPositiveButton("Got it",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int which) {
												confirmPhoneNumber();

											}
										}).show();

					}
				} else {
					new AlertDialog.Builder(UserRegistration.this)
							.setTitle("No Internet")
							.setIcon(android.R.drawable.ic_dialog_alert)
							.setMessage(
									"You'll need an internet connection to register")
							.setPositiveButton("Alright",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									}).show();
				}
			}
		});
	}

	private void confirmPhoneNumber() {
		// TODO Auto-generated method stub
		try {
			setSupportProgressBarIndeterminateVisibility(true);
			final SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(phoneNumber, null, phoneNumber, null, null);
			startReciever();

		} catch (Exception e) {
			// TODO: handle exception
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.toast,
					(ViewGroup) findViewById(R.id.toast_layout_root));
			TextView text = (TextView) layout.findViewById(R.id.text);
			text.setText("Woops! Something went wrong. Please try again.");
			Toast toast = new Toast(getApplicationContext());
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
		}
	}

	private void startReciever() {
		// TODO Auto-generated method stub
		SaveOrRetrieveLoginDetails.storeUpRegistrationDetails(context,
				phoneNumber, password, username);
		Intent intent = new Intent("action.receiver");
		Bundle b= new Bundle();
		b.putString("phoneNumber", phoneNumber);
		sendBroadcast(intent);

		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast,
				(ViewGroup) findViewById(R.id.toast_layout_root));
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText("Confirming your number...");
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
