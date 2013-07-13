package com.Dashkevich.chat;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public abstract class BaseActivity extends Activity {
	private ServiceConnection mSrvConn;
	protected ChatService mCore;
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mIntent = new Intent();
		mIntent.setClass(this, ChatService.class);
		startService(mIntent);
		mSrvConn = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				finish();
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				mCore = ((ChatService.SelfBinder) service).srv;
				onConnectedToService();
			}
		};
		bindService(mIntent, mSrvConn, Service.BIND_AUTO_CREATE);
	}

	@Override
	protected void onDestroy() {
		unbindService(mSrvConn);
		super.onDestroy();
	}
	abstract protected void onConnectedToService();
	protected final void stopSystem () {
		stopService(mIntent);
	}
}
