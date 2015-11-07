package com.populyx.websocket;

import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import com.populyx.controller.MainController;
import com.populyx.entities.User;
import com.populyx.security.CustomEvent;

public class WebSocketConnectHandler implements ApplicationListener<CustomEvent> {
	@Autowired
	private ActiveWebSocketUsers repository;
	
	@Autowired
	MainController chatController;
	
	public void onApplicationEvent(CustomEvent event) {
		User user = event.getUser();
		if(user.getUsername().equals("Daniel")||user.getUsername().equals("user")){
			user.setId(1L);
			user.setFirstName("Daniel");
			user.setLastName("Benitez");
			user.setEmail("dbenitez83@live.com");
		}else if(user.getUsername().equals("Marta")||user.getUsername().equals("user2")){
			user.setId(2L);
			user.setFirstName("Marta");
			user.setLastName("Varillas");
			user.setEmail("mvarillas3@live.com");
		}else if(user.getUsername().equals("Amigo")){
			user.setId(3L);
			user.setFirstName("Amigo");
			user.setLastName("Nuevo");
			user.setEmail("as@live.com");
		}else{
			return;
		}
		repository.add(new ActiveWebSocketUser(user.getId(), user.getFirstName(), Calendar.getInstance()));
		chatController.getMessagingTemplate().convertAndSend("/topic/friends/signin", Arrays.asList(user.getFirstName()));
	}
}