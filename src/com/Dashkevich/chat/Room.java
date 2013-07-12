package com.Dashkevich.chat;

import com.Dashkevich.chat.R.drawable;

public class Room {
	public final String name;

	public enum Status {
		inside, ok, banned
	};

	private Status mStatus;
	private int mPeople;
	public Room(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return mStatus;
	}

	public Room setStatus(Status mStatus) {
		this.mStatus = mStatus;
		return this;
	}

	public int getPeople() {
		return mPeople;
	}

	public Room setPeople(int mPeople) {
		this.mPeople = mPeople;
		return this;
	}
}
