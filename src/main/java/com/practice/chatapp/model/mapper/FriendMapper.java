package com.practice.chatapp.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.practice.chatapp.model.domain.Friend;

@Mapper
public interface FriendMapper {
	
	List<Friend> AddFriend(
		@Param("userA") long userA,
		@Param("userB") long userB
	);
	
	List<Friend> findByUserId(
		@Param("userId") long userId
	);
	
	List<Friend> findByUserAAndUserB(
		@Param("userA") long userHigh,
		@Param("userB") long userLow
	);
	
	
}
