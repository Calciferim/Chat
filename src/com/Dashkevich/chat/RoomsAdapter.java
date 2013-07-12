package com.Dashkevich.chat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RoomsAdapter extends BaseAdapter {
	private final Context context;
	private final List<Room> listroom;

	public RoomsAdapter(Context context, List<Room> listroom) {
		this.context = context;
		this.listroom = listroom;
	}

	@Override
	public int getCount() {
		return listroom.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listroom.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View maskedViem, ViewGroup parent) {
		Room item = listroom.get(position);
		if (maskedViem == null) {
			maskedViem = LayoutInflater.from(context).inflate(
					R.layout.rooms_row, null);
		}
		TextView nameView = (TextView) maskedViem.findViewById(R.id.roomname);
		nameView.setText(item.name);

		TextView peopleView = (TextView) maskedViem.findViewById(R.id.online);
		peopleView.setText(String.valueOf(item.getPeople()));

		TextView numberView = (TextView) maskedViem.findViewById(R.id.number);
		numberView.setText(String.valueOf(position + 1));

		if (position % 2 == 1) {
			maskedViem.setBackgroundColor(0x99999999);
		} else {
			maskedViem.setBackgroundColor(0x99555555);
		}
		ImageView statusView = (ImageView) maskedViem
				.findViewById(R.id.imgStatus);
		switch (item.getStatus()) {
		case inside:
			statusView.setImageResource(R.drawable.green);
			break;
		case ok:
			statusView.setImageResource(R.drawable.yellow);
			break;
		case banned:
			statusView.setImageResource(R.drawable.red);
			break;
		}
		return maskedViem;
	}


}
