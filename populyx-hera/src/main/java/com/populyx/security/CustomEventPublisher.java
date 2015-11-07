package com.populyx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.populyx.cerbero.service.CerberoService;
import com.populyx.entities.User;

@Component
public class CustomEventPublisher implements ApplicationEventPublisherAware {

	@Autowired
	private CerberoService cerberoService;

	private ApplicationEventPublisher publisher;

	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public void publish(User user) {
//		cerberoService.registerUserLogin(user.getId());
		publisher.publishEvent(new CustomEvent(this, user));
	}
}