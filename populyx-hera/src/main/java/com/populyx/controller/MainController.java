package com.populyx.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.populyx.dto.ChatMessage;
import com.populyx.entities.User;
import com.populyx.security.CustomEventPublisher;
import com.populyx.websocket.ActiveWebSocketUser;
import com.populyx.websocket.ActiveWebSocketUsers;

@Controller
public class MainController {
	@Autowired
	private ActiveWebSocketUsers activeWebSocketUsers;

	@Autowired
	CustomEventPublisher cvp;

	private SimpMessagingTemplate messagingTemplate;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String viewApplication() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/maptemplate")
	public String mapTemplate(){
		return "mapTemplate";
	}
	
	@RequestMapping("/maintemplate")
	public String mainTemplate(){
		return "mainTemplate";
	}
	
	@Autowired
	public MainController(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@MessageMapping("/chat")
	@SendToUser
	public void im(ChatMessage chatMessage) {
		String authedSender = chatMessage.getFrom();
		String recipient = chatMessage.getTo();
		if (!authedSender.equals(recipient)) {
			messagingTemplate.convertAndSendToUser(recipient, "/queue/messages", chatMessage);
		}

		messagingTemplate.convertAndSendToUser(authedSender, "/queue/messages", chatMessage);
	}
	
	@RequestMapping(value ="/logging", method=RequestMethod.GET)
	public @ResponseBody User logging(Principal principal) {
		String name = principal.getName();
		User user=new User();
		user.setUsername(name);
		cvp.publish(user);
		return user;
	}

	@SubscribeMapping("/users")
	public List<String> subscribeMessages() throws Exception {
		List<String> activeUsers = new ArrayList<String>();
			for (ActiveWebSocketUser activeUser : activeWebSocketUsers.findAllActiveUsers()) {
				activeUsers.add(activeUser.getUsername());
			}
		return activeUsers;
	}

	public SimpMessagingTemplate getMessagingTemplate() {
		return messagingTemplate;
	}

}
