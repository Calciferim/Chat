package com.Dashkevich.chat;

import com.Dashkevich.chat.API.APIexception;
import com.Dashkevich.chat.API.AuthCallback;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_auth);
		findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				OnLoginClick(v);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.auth, menu);
		return true;
	}

	public void ShowAbout() {
		AlertDialog About = new AlertDialog.Builder(this).create();
		About.setTitle("Chat for Android");
		About.setIcon(R.drawable.chatsmall);
		About.setMessage("Copyright (c) 2013\nDashkevich Inc.\nAll rights reserved");
		About.setButton("OK", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

			}
		});
		About.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about_chat:
			ShowAbout();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	public void OnLoginClick(View view) {
		EditText Email = (EditText) findViewById(R.id.editText1);
		EditText Pass = (EditText) findViewById(R.id.editText2);
			mCore.getAPI().auth(Email.getText().toString(),
					Pass.getText().toString(), new AuthCallback() {
						
						@Override
						public void AuthCallbackSuccess() {
							Intent i=new Intent(AuthActivity.this, RoomsActivity.class);
							startActivity(i);
							finish();
							
						}
						
						@Override
						public void AuthCallbackFaild(String message) {						
							Toast.makeText(AuthActivity.this, message, Toast.LENGTH_LONG).show();
							
						}
					});
	}

	public void OnRegisterClick(View view) {
		Intent inte = new Intent();
		inte.setClass(this, RegActivity.class);
		startActivity(inte);
	}

	@Override
	protected void onConnectedToService() {

	}

}
