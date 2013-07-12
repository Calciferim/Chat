package com.Dashkevich.chat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter {

	private final Context context;
	private final List<Message> listmessage;

	public MessageAdapter(Context context, List<Message> listmessage) {
		this.context=context;
		this.listmessage=listmessage;
	}
	
	@Override
	public int getCount() {
		return listmessage.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listmessage.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View maskedViem, ViewGroup parent) {
		Message msg=listmessage.get(position);
		if (maskedViem == null) {
			maskedViem = LayoutInflater.from(context).inflate(
					R.layout.message_row, null);
		}
		TextView nickView = (TextView) maskedViem.findViewById(R.id.Nick);
		nickView.setText("Calcifer");
		TextView timeView = (TextView) maskedViem.findViewById(R.id.Time);
		timeView.setText(String.valueOf(msg.time));
		TextView msgView = (TextView) maskedViem.findViewById(R.id.Message);
		msgView.setText(msg.getText());
		return maskedViem;
	}

}
