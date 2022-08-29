package com.practice.chatapp.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.practice.chatapp.model.domain.User;

@Mapper
public interface UserMapper {
	
	List<User> RegisterUser(
		@Param("userName") String userName,
		@Param("password") String password
	);
	
	List<User> findByUserNameAndPassword(
		@Param("userName") String userName,
		@Param("password") String password
	);
	
	List<User> findByUserIdAndUserName(
		@Param("userId") long userId,
		@Param("userName") String userName
	);
	
	User findByUserId(
		@Param("userId") long userId
	);
}
