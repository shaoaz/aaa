package com.qphone.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qphone.pojo.User;
import com.qphone.service.UserService;

@Controller
@RequestMapping("/aa")
public class OrderController {
	@Autowired
	private UserService serService;

	@RequestMapping(value = "/design", method = RequestMethod.POST)
	public String getdesign(User user) {
		int a = serService.createUser(user);
		System.out.println(a);
		return "/login";
	}

	@RequestMapping(value="/lg",method=RequestMethod.POST)
	public String get(User user) {
		// securityManager交给spring管理
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subject.login(token);
			return "index";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "/login";
		}
	}

}
