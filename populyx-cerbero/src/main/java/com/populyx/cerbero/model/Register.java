package com.populyx.cerbero.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Register implements Serializable {
	
	private static final long serialVersionUID = 1434234234L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int userId;
	
	private Date logInAt;
	private Date logOffAt;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getLogInAt() {
		return logInAt;
	}
	public void setLogInAt(Date logInAt) {
		this.logInAt = logInAt;
	}
	public Date getLogOffAt() {
		return logOffAt;
	}
	public void setLogOffAt(Date logOffAt) {
		this.logOffAt = logOffAt;
	}
	

}
