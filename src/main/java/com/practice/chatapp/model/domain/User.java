package com.practice.chatapp.model.domain;

public class User {
	
	private long id;
	private String userName;
	private String password;
	
	//id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	//userName
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//password
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
