package com.practice.chatapp.model.form;

import java.io.Serializable;

public class SendMessageForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long sayUser;
	private long listenUser;
	private String message;
	
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
	
}
