package com.practice.chatapp.model.dto;

import com.practice.chatapp.model.domain.User;

public class FriendListDto {
	
	private long userId;
	private String userName;
	
	public FriendListDto() {}
	
	public FriendListDto(User user) {
		this.userId = user.getId();
		this.userName = user.getUserName();
	}
	
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
