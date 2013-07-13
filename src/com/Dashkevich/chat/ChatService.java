package com.Dashkevich.chat;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ChatService extends Service {

	private API mAPI = new API();

	@Override
	public IBinder onBind(Intent intent) {

		return new SelfBinder(this);
	}

	public static class SelfBinder extends Binder {
		public final ChatService srv;

		SelfBinder(ChatService srv) {
			this.srv = srv;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mAPI = new API();
	}
	public API getAPI (){
		return mAPI;
	}
@Override
public void onDestroy() {
	mAPI=null;
	super.onDestroy();
}
}
