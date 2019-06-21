package com.qphone.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qphone.dao.UserMapper;
import com.qphone.pojo.User;
import com.qphone.service.UserService;
import com.qphone.utils.PasswordHelper;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	private PasswordHelper p = new PasswordHelper();
	@Override
	public int createUser(User user) {
		// º”√‹√‹¬Î
		p.encryptPassword(user);
		System.out.println(user);
		int a=userMapper.createUser(user);
		return a;
	}



	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

}
