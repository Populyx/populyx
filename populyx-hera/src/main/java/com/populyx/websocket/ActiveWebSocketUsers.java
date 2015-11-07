package com.populyx.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ActiveWebSocketUsers  {
	
	private List<ActiveWebSocketUser> allActiveUsers=new ArrayList<ActiveWebSocketUser>();

	public List<ActiveWebSocketUser> findAllActiveUsers(){
		return allActiveUsers;
	}
	
	public void add(ActiveWebSocketUser user){
		if(!allActiveUsers.contains(user.getUsername())){
			allActiveUsers.add(user);
		}
	}
}
