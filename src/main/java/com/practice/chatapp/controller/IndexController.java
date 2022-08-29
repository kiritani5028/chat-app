package com.practice.chatapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.practice.chatapp.model.domain.Chat;
import com.practice.chatapp.model.domain.Friend;
import com.practice.chatapp.model.domain.User;
import com.practice.chatapp.model.dto.ChatListDto;
import com.practice.chatapp.model.dto.FriendListDto;
import com.practice.chatapp.model.form.AddFriendForm;
import com.practice.chatapp.model.form.LoginForm;
import com.practice.chatapp.model.form.RegisterForm;
import com.practice.chatapp.model.form.SendMessageForm;
import com.practice.chatapp.model.form.TalkRoomForm;
import com.practice.chatapp.model.mapper.ChatMapper;
import com.practice.chatapp.model.mapper.FriendMapper;
import com.practice.chatapp.model.mapper.UserMapper;
import com.practice.chatapp.model.session.LoginSession;

@Controller
@RequestMapping("/chatApp")
public class IndexController {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	FriendMapper friendMapper;
	
	@Autowired
	ChatMapper chatMapper;
	
	@Autowired
	LoginSession loginSession;
	
	Gson gson = new Gson();
	
	@RequestMapping("/register")
	public String Register(Model m) {
		m.addAttribute("loginSession", loginSession);
		
		return "register";
	}
	
	@ResponseBody
	@RequestMapping("/api/register")
	public String RegisterApi(@RequestBody RegisterForm f) {
		userMapper.RegisterUser(f.getUserName(), f.getPassword());
		
		return null;
	}
	
	@RequestMapping("/login")
	public String Login(Model m) {
		m.addAttribute("loginSession", loginSession);
		
		return "login";
	}
	
	@RequestMapping("/logout")
	public String Logout(Model m) {
		loginSession.setId(0);
		loginSession.setUserName(null);
		loginSession.setPassword(null);
		
		m.addAttribute("loginSession", loginSession);
		
		
		return "login";
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public String Index(@ModelAttribute("LoginForm") LoginForm f, Model m) {
		List<User> users = userMapper.findByUserNameAndPassword(f.getUserName(), f.getPassword());
			
			if(users.size() > 0) {
				User user = users.get(0);
				loginSession.setId(user.getId());
				loginSession.setUserName(user.getUserName());
				loginSession.setPassword(user.getPassword());
				
				List<Friend> friends = friendMapper.findByUserId(loginSession.getId());
				List<FriendListDto> dto = new ArrayList<FriendListDto>();
				
				friends.forEach((friend) -> {
					if(friend.getUserA() != loginSession.getId()) {
						FriendListDto d = new FriendListDto(userMapper.findByUserId(friend.getUserA()));
						
						dto.add(d);
					}else {
						FriendListDto d = new FriendListDto(userMapper.findByUserId(friend.getUserB()));
						
						dto.add(d);
					}
				});
				m.addAttribute("json", gson.toJson(dto));
				m.addAttribute("loginSession", loginSession);
				
				return "index";
			}else {
				m.addAttribute("loginSession", loginSession);
				
				return "login";
			}
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String indexGet(Model m) {
		if(loginSession.getId() > 0 ) {
			List<Friend> friends = friendMapper.findByUserId(loginSession.getId());
			List<FriendListDto> dto = new ArrayList<FriendListDto>();
			
			friends.forEach((friend) -> {
				if(friend.getUserA() != loginSession.getId()) {
					FriendListDto d = new FriendListDto(userMapper.findByUserId(friend.getUserA()));
					
					dto.add(d);
				}else {
					FriendListDto d = new FriendListDto(userMapper.findByUserId(friend.getUserB()));
					
					dto.add(d);
				}
			});
			m.addAttribute("json", gson.toJson(dto));
			m.addAttribute("loginSession", loginSession);
			
			long number = (long) Math.floor(Math.random() * -900_000_000L) - 100_000_000L;
			
			m.addAttribute("message", Long.toString(number));
			
			return "index";
		}
		m.addAttribute("loginSession", loginSession);
		return "login";
	}
	
	@ResponseBody
	@RequestMapping("/api/addFriend")
	public String AddFriendApi(@RequestBody AddFriendForm f) {
		long userA = loginSession.getId();
		
		List<User> users = userMapper.findByUserIdAndUserName(f.getUserId(), f.getUserName());
		if(users.size() > 0) {
			//指定したユーザーのidを取得
			long userB = users.get(0).getId();
			long userHigh = Math.max(userA, userB);
			long userLow = Math.min(userA, userB);
			
			//指定したユーザーとすでにフレンドかを確認
			List<Friend> sameFriend = friendMapper.findByUserAAndUserB(userHigh, userLow);
			
			if(sameFriend.size() == 0) {
				//フレンドを追加
				friendMapper.AddFriend(userHigh, userLow);
				
				//フレンド一覧を取得
				List<Friend> friends = friendMapper.findByUserId(loginSession.getId());
				List<FriendListDto> dto = new ArrayList<FriendListDto>();
				
				friends.forEach((friend) -> {
					if(friend.getUserA() != loginSession.getId()) {
						FriendListDto d = new FriendListDto(userMapper.findByUserId(friend.getUserA()));
						
						dto.add(d);
					}else {
						FriendListDto d = new FriendListDto(userMapper.findByUserId(friend.getUserB()));
						
						dto.add(d);
					}
				});
				
				
				return gson.toJson(dto);
			}else {
				return "そのユーザーとはすでにフレンドです。";
			}
		}
		
		return "ユーザーが見つかりませんでした。";
	}
	
	@RequestMapping("/talkRoom")
	public String TalkRoom(@ModelAttribute("TalkRoomForm") TalkRoomForm f ,Model m) {
		User loginUser = userMapper.findByUserId(loginSession.getId());
		User friendUser = userMapper.findByUserId(f.getFriendId());
		
		if(loginUser != null && friendUser != null) {
			m.addAttribute("friendName", friendUser.getUserName());
			m.addAttribute("friendId", friendUser.getId());
			m.addAttribute("loginSession", loginSession);
			
			//ログインユーザーとフレンドユーザーが関係しているメッセージを取得
			List<Chat> chats = chatMapper.findBySayUserAndListenUser(loginUser.getId(), friendUser.getId());
			
			List<ChatListDto> dto = new ArrayList<ChatListDto>();
			
			chats.forEach((chat) -> {
				ChatListDto d = new ChatListDto(chat, friendUser, loginUser);
				dto.add(d);
			});
			
			dto.sort((e1, e2) -> e2.getCreatedAt().compareTo(e1.getCreatedAt()));
			
			m.addAttribute("json", gson.toJson(dto));
			
			return "talkRoom";
		} else {
			return "redirect:/chatApp/";
		}

	}
	
	@ResponseBody
	@RequestMapping("/api/sendMessage")
	public String SendMessageApi(@RequestBody SendMessageForm f, Model m) {
		chatMapper.InsertChat(f.getSayUser(), f.getListenUser(), f.getMessage());
		
		User loginUser = userMapper.findByUserId(loginSession.getId());
		User friendUser = userMapper.findByUserId(f.getListenUser());
		
		//ログインユーザーとフレンドユーザーが関係しているメッセージを取得
		List<Chat> chats = chatMapper.findBySayUserAndListenUser(loginUser.getId(), friendUser.getId());
		
		List<ChatListDto> dto = new ArrayList<ChatListDto>();
		
		chats.forEach((chat) -> {
			ChatListDto d = new ChatListDto(chat, friendUser, loginUser);
			dto.add(d);
		});
		
		dto.sort((e1, e2) -> e2.getCreatedAt().compareTo(e1.getCreatedAt()));
		
		return gson.toJson(dto);
	}
}
















