package com.qphone.dao;

import com.qphone.pojo.User;
import com.qphone.pojo.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	public int createUser(User user); // �����˻�

	public User findByUsername(String username);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}