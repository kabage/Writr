package com.writr.backend;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Master extends Service {

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public void onCreate() {

		super.onCreate();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("service started", "started");
		
	
		return super.onStartCommand(intent, flags, startId);
	}

}
