package com.bokesoft.yigo2.ext.webservice.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import com.alibaba.fastjson.JSON;
import com.bokesoft.yigo2.ext.webservice.demo.intf.UserService;
import com.bokesoft.yigo2.ext.webservice.demo.struc.User;

@WebService(targetNamespace="http://localhost:8080/",endpointInterface = "com.bokesoft.yigo2.ext.webservice.demo.intf.UserService")
public class UserServiceImpl implements UserService {

	private Map<String, User> userMap = new HashMap<String, User>();

	public UserServiceImpl() {
		System.out.println("向实体类插入数据");
		User user = new User();
		user.setUserId("411001");
		
		
		user.setUserName("zhansan");
		user.setAge("20");
		user.setUpdateTime(new Date());
		userMap.put(user.getUserId(), user);

		user = new User();
		user.setUserId("411002");
		user.setUserName("lisi");
		user.setAge("30");
		user.setUpdateTime(new Date());
		userMap.put(user.getUserId(), user);

		user = new User();
		user.setUserId("411003");
		user.setUserName("wangwu");
		user.setAge("40");
		user.setUpdateTime(new Date());
		userMap.put(user.getUserId(), user);
	}

	@Override
	public String getName(String userId) {
		return "liyd-" + userId;
	}

	@Override
	public User getUser(String userId) {
		User user = userMap.get(userId);
		return user;
	}

	@Override
	public ArrayList<User> getAllUser() {
		ArrayList<User> users = new ArrayList<>();
		userMap.forEach((key, value) -> {
			users.add(value);
		});
		return users;
	}
	
	@Override
	public void dealUserRequest(User user) {
		System.out.println(JSON.toJSON(user));
	}
}
