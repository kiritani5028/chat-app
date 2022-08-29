package com.practice.chatapp.model.domain;

import java.sql.Timestamp;

public class Chat {
	
	private long id;
	private long sayUser;
	private long listenUser;
	private String message;
	private Timestamp createdAt;
	
	//id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	//sayUser
	public long getSayUser() {
		return sayUser;
	}
	public void setSayUser(long sayUser) {
		this.sayUser = sayUser;
	}
	
	//listenUser
	public long getListenUser() {
		return listenUser;
	}
	public void setListenUser(long listenUser) {
		this.listenUser = listenUser;
	}
	
	//message
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	//createdAt
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
