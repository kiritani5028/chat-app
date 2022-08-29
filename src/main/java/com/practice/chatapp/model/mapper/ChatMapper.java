package com.practice.chatapp.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.practice.chatapp.model.domain.Chat;

@Mapper
public interface ChatMapper {
	List<Chat> InsertChat(
		@Param("sayUser") long sayUser,
		@Param("listenUser") long listenUser,
		@Param("message") String message
	);
	
	List<Chat> findBySayUserAndListenUser(
		@Param("loginUser") long loginUser,
		@Param("friendUser") long friendUser
	);
}
