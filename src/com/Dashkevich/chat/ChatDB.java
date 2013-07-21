package com.Dashkevich.chat;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDB extends SQLiteOpenHelper {
	public static final String FIELD_ID="id";
	public static final String FIELD_NAME="name";

	public ChatDB(Context context) {
		super(context, "chat", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS Messages (id INT PRIMARY KEY, time LONG, text_msg TEXT, sender_id INT, room_id INT);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Rooms ("+FIELD_ID+" INT PRIMARY KEY, "+FIELD_NAME+" VARCHAR (255))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS Messages;");
		db.execSQL("DROP TABLE IF EXISTS Rooms;");
		onCreate(db);
	}

	public void insertMessage(int id, String msg, int sender_id, int room_id) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("INSERT INTO Messages (id, text_msg, time, sender_id, room_id) VALUES ("
				+ id
				+ ",\""
				+ msg
				+ "\","
				+ System.currentTimeMillis()
				+ ","
				+ sender_id + "," + room_id + ");");
	}
	public void selectAll(){
		Cursor c= getReadableDatabase().rawQuery("SELECT id, text_msg, time, sender_id, room_id FROM Messages;", new String[0]);
		while(c.moveToNext()){
			int id=c.getInt(0);
			String text=c.getString(1);
			long time=c.getLong(2);
			int sender_id=c.getInt(3);
			int room_id=c.getInt(4);
			Log.i("ChatDb", "id: "+id+", msg: "+text+", time: "+time+", sender_id: "+sender_id+", room_id: "+room_id);
		}
	}
	public Cursor fetchRooms (){
		return getReadableDatabase().rawQuery("SELECT "+FIELD_ID+", "+FIELD_NAME+" FROM Rooms;", new String[0]);
	}
}
