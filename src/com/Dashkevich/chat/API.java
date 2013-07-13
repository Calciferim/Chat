package com.Dashkevich.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.Dashkevich.chat.Parser.ParserException;

import android.util.Log;

public class API {
	private AuthInfo mAI;
	private boolean isAuth = false;
	private static final String BASE_URL="http://10.2.1.38:6606/";

	public static String connect(String url) {

		HttpClient httpclient = new DefaultHttpClient();

		// Prepare a request object
		HttpGet httpget = new HttpGet(url);

		// Execute the request
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			// Examine the response status
			Log.i("Praeda", response.getStatusLine().toString());

			// Get hold of the response entity
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to worry about connection release

			if (entity != null) {

				// A Simple JSON Response Read
				InputStream instream = entity.getContent();
				String result = convertStreamToString(instream);
				// now you have the string representation of the HTML request
				instream.close();

				return result;
			} else {
				throw new RuntimeException("error");
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public void reg(Person p) {
		String resp = connect(BASE_URL+"reg?email="
				+ p.getmEmail() + "&pass=" + p.getmPass() + "&sex="
				+ p.getmSex() + "&age=" + p.getmAge() + "&nick=" + p.getmNick());
		
	}

	public void auth(String email, String pass) throws APIexception {
		if (!isAuth) {

			try {
				String resp = connect(BASE_URL+"auth?email="
						+ email + "&pass=" + pass);
				mAI = Parser.auth(resp);
			}catch (ParserException p){
				switch (p.err_code) {
				case 1:
					
					break;

				default:
					break;
				}
				
			} catch (Exception e) {
				throw new APIexception(e);
			}
			isAuth = true;
		} else {
			throw new APIexception("the user is already logged in");
		}
	}

	class APIexception extends Exception {

		public APIexception(Throwable t) {
			super(t);
		}

		public APIexception(String string) {
			super(string);
		}

	}
	public List<Room> getRooms () throws ParserException {
		String resp=connect(BASE_URL+"rooms?token="+mAI.mToken);
		List<Room> list = new ArrayList<Room>();
		Parser.getRooms(resp, list);
		return list;
	}
	public void addRoom (String name) {
		String resp=connect(BASE_URL+"addroom?token="+mAI.mToken+"&name="+name);
	}
}
