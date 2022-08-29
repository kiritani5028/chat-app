package com.practice.chatapp.model.form;

import java.io.Serializable;

public class TalkRoomForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long friendId;
	
	//friendId
	public long getFriendId() {
		return friendId;
	}
	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

}
