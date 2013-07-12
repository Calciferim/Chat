package com.Dashkevich.chat;

public class Message {
	private String sender;
	private String resiver;
	private String text;
	public final long time;
	
	public Message(String sender, String resiver, String text) {
		this.resiver=resiver;
		this.sender=sender;
		this.text=text;
		this.time=System.currentTimeMillis();
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getResiver() {
		return resiver;
	}

	public void setResiver(String resiver) {
		this.resiver = resiver;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
