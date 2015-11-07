package com.populyx.security;

import org.springframework.context.ApplicationEvent;

import com.populyx.entities.User;

public class CustomEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 34827275985L;
	private User user;

	public CustomEvent(Object source, User user) {
		super(source);
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public String toString() {
		return "My Custom Event";
	}
}