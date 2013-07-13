package com.Dashkevich.chat;

import java.util.ArrayList;
import java.util.List;

import com.Dashkevich.chat.Parser.ParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RoomsActivity extends BaseActivity {
	private RoomsAdapter mAdapter;
	private ListView LV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_rooms);
		  
	}

	@Override
	protected void onConnectedToService() {
		try {
			mAdapter = new RoomsAdapter(this, mCore.getAPI().getRooms());
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LV = (ListView) findViewById(R.id.list);
		LV.setAdapter(mAdapter);
		LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			  
			  @Override public void onItemClick(AdapterView<?> adapter, View v, int
			  position, long itemid) { 
				  Intent inte = new Intent();
					inte.setClass(RoomsActivity.this, ChatActivity.class);
					startActivity(inte);
			  }
			  
			  });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.rooms, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_room:
			showDialog(1);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (1 == id) {
			return ShowAddRoom();
		}
		return super.onCreateDialog(id);
	}

	@Override
	public void onBackPressed() {
	//	android.os.Process.killProcess(android.os.Process.myPid());
		stopSystem();
		super.onBackPressed();
	}

	public Dialog ShowAddRoom() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Add room");
		final View view = LayoutInflater.from(this).inflate(
				R.layout.menu_rooms, null);
		builder.setView(view);
		EditText MB = (EditText) view.findViewById(R.id.dialog_Desk);
		MB.setFilters(new InputFilter[] { new InputFilter() {

			@Override
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				String text = source.toString() + dest.toString();
				String[] arr = text.split("\n");
				if ((arr != null && arr.length > 3) || text.length() > 36) {
					return "";
				}
				return null;
			}
		} });
		builder.setPositiveButton("Save",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText Name = (EditText) view
								.findViewById(R.id.dialog_Name);
						EditText Desk = (EditText) view
								.findViewById(R.id.dialog_Desk);
						if (Name.getText().toString() == null
								|| Name.getText().toString().length() == 0
								|| Desk.getText().toString() == null
								|| Desk.getText().toString().length() == 0) {
							Toast.makeText(RoomsActivity.this,
									"Name or desk is not filled",
									Toast.LENGTH_SHORT).show();
						} else {
							// Õ≈ –¿¡Œ“¿≈“!!!!
							mCore.getAPI().addRoom(Name.getText().toString());
							// Õ≈ –¿¡Œ“¿≈“!!!!
							mAdapter.notifyDataSetChanged();
						}
					}
				});
		builder.setNegativeButton("Cancel", null);
		return builder.create();
	}

}
