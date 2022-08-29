package com.practice.chatapp.model.form;

import java.io.Serializable;

public class AddFriendForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long userId;
	private String userName;
	
	//userId
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	//userName
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
