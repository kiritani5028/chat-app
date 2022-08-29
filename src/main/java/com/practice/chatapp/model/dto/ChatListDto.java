package com.practice.chatapp.model.dto;

import java.sql.Timestamp;

import com.practice.chatapp.model.domain.Chat;
import com.practice.chatapp.model.domain.User;

public class ChatListDto {
	
	private long sayUser;
	private long listenUser;
	private String friendName;
	private String loginName;
	private String message;
	private Timestamp createdAt;
	
	public ChatListDto() {}
	
	public ChatListDto(Chat chat, User friendUser, User loginUser) {
		this.sayUser = chat.getSayUser();
		this.listenUser = chat.getListenUser();
		this.friendName = friendUser.getUserName();
		this.loginName = loginUser.getUserName();
		this.message = chat.getMessage();
		this.createdAt = chat.getCreatedAt();
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
	
	//friendName
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	
	//loginName
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
