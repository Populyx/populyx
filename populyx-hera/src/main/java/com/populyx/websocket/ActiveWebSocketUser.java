package com.populyx.websocket;

import java.util.Calendar;

public class ActiveWebSocketUser {
	private long id;

	private String username;

	private Calendar connectionTime;

	public ActiveWebSocketUser() {}

	public ActiveWebSocketUser(long id, String username, Calendar connectionTime) {
		super();
		this.id = id;
		this.username = username;
		this.connectionTime = connectionTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Calendar getConnectionTime() {
		return connectionTime;
	}

	public void setConnectionTime(Calendar connectionTime) {
		this.connectionTime = connectionTime;
	}


}
