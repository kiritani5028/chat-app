package com.practice.chatapp.model.domain;

public class Friend {
	
	private long id;
	private long userA;
	private long userB;
	
	//id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	//userA
	public long getUserA() {
		return userA;
	}
	public void setUserA(long userA) {
		this.userA = userA;
	}
	
	//userB
	public long getUserB() {
		return userB;
	}
	public void setUserB(long userB) {
		this.userB = userB;
	}
}
