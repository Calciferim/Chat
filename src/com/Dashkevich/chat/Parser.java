package com.Dashkevich.chat;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.Dashkevich.chat.Room.Status;

public class Parser {
	public static AuthInfo auth(String jsonString) throws ParserException {
		try {
			JSONObject json = new JSONObject(jsonString);
			if (json.getString("status").equals("ok")) {
				return new AuthInfo(json.getString("token"),
						json.getString("nick"));
			} else {
				throw new ParserException(json.getInt("err_code"));
			}
		} catch (JSONException e) {
			throw new ParserException(e);
		}
	}

	public static void reg(String jsonString) throws ParserException {
		try {
			JSONObject json = new JSONObject(jsonString);
			if (json.getString("status").equals("ok")) {

			} else {
				if (json.getString("err_code").equals("1")) {

				}
			}
		} catch (JSONException e) {
			throw new ParserException(e);
		}
	}

	static class ParserException extends Exception {
		public final int err_code;

		public ParserException(int errcode) {
			err_code = errcode;
		}

		public ParserException(String message) {
			super(message);
			err_code = -1;
		}

		public ParserException(Throwable t) {
			super(t);
			err_code = -1;
		}
	}
	public static void getRooms(String jsonString, List<Room> list) throws ParserException{
		try {
			JSONObject json = new JSONObject(jsonString);
			if (json.getString("status").equals("ok")) {
				JSONArray rooms=json.getJSONArray("rooms");
				for (int i = 0; i < rooms.length(); i++) {
					JSONObject j=rooms.getJSONObject(i);
					list.add(new Room(j.getString("name")).setPeople(j.getInt("people_count")).setStatus(Status.valueOf(j.getString("status"))));
				}
			} else {
				if (json.getString("err_code").equals("1")) {

				}
			}
		} catch (JSONException e) {
			throw new ParserException(e);
		}
	}
}
