package com.populyx.cerbero.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.populyx.cerbero.dao.UserDAO;
import com.populyx.entities.UserRole;

@Service("MyUserService")
public class MyUserService implements UserDetailsService {

	// get user from the database, via Hibernate
	@Autowired
	private UserDAO userDao;

//	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

		com.populyx.entities.User user = userDao.findByUserName( username);
		Set<UserRole> userRoles=new HashSet<UserRole>();
		UserRole userRole=new UserRole();
		userRole.setRole("ROLE_USER");
		userRoles.add(userRole);
		List<GrantedAuthority> authorities = buildUserAuthority(userRoles);

		return buildUserForAuthentication(user, authorities);

	}

	private User buildUserForAuthentication(com.populyx.entities.User user, List<GrantedAuthority> authorities) {
		User userLogged=new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
		return userLogged;
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority(userRoles.iterator().next().getRole()));
		// Build user's authorities
//		for (UserRole userRole : userRoles) {
//			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
//		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}