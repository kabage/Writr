package com.writr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsListener extends BroadcastReceiver {

	String strMsgSrc, strMsgBody;
	final SmsManager sms = SmsManager.getDefault();

	@Override
	public void onReceive(Context context, Intent intent) {

		Bundle extras = intent.getExtras();

		try {
			if (extras != null) {

				Object[] smsextras = (Object[]) extras.get("pdus");
				for (int i = 0; i < smsextras.length; i++) {
					SmsMessage smsmsg = SmsMessage
							.createFromPdu((byte[]) smsextras[i]);
					strMsgBody = smsmsg.getMessageBody().toString();
					strMsgSrc = smsmsg.getOriginatingAddress();
				}
				if (strMsgBody.trim().startsWith("+")) {
					if (strMsgSrc.trim().equals(strMsgBody.trim())) {
						Log.i("success getting txt", strMsgBody);

						Intent finish = new Intent(context,
								SetProfilePicture.class);
						Bundle b = new Bundle();

						b.putString("phoneNumber", strMsgSrc);
						finish.putExtras(b);
						finish.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(finish);

					}

				}

			}

		} catch (Exception e) {
			Log.e("an error occured in smslisener", e.toString());

		}
	}
}
