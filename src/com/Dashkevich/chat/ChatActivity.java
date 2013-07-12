package com.Dashkevich.chat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

public class ChatActivity extends Activity {
	private final static List<Message> sMessage = new ArrayList<Message>();
	private ListView LV;
	private MessageAdapter mAdapter;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat);
		LV = (ListView) findViewById(R.id.listChat);
		mAdapter = new MessageAdapter(this, sMessage);
		LV.setAdapter(mAdapter);
	}
	public void OnSendClick (View View){
		EditText MsgB = (EditText) findViewById(R.id.MessageBox);
		String Text = MsgB.getText().toString();
		if (Text.length()==0){
			return;
		}
		sMessage.add(new Message("Calcifer", "", Text));
		mAdapter.notifyDataSetChanged();
		MsgB.setText(null);
		LV.setSelection(mAdapter.getCount());
	
	}
}
