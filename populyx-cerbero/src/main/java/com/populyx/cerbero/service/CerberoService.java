package com.populyx.cerbero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.populyx.cerbero.dao.RegisterDAO;

@Service
public class CerberoService {
	
	@Autowired
	private RegisterDAO registerDAO;

	public void registerUserLogin(Long userId){
		registerDAO.registerUserLogin(userId);
	}
	
	public void unRegisterUserLogin(Long userId){
		registerDAO.unRegisterUserLogin(userId);
	}
	

	
}
