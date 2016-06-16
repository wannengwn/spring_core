package com.wn.webapp.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wn.webapp.user.entity.User;
import com.wn.webapp.user.service.UserService;
import com.wn.webapp.utils.DateUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/saveUser",method = RequestMethod.GET)
	public String saveUser(HttpServletRequest request,Model model){
		User user = new User();
		user.setUserName("管理员");
		user.setLoginName("admin");
		user.setPassWord("admin");
		user.setCreateUser("admin");
		user.setCreateTime(DateUtil.getNow());
		user.setUpdateUser("admin");
		user.setUpdateTime(DateUtil.getNow());
		userService.save(user);
		model.addAttribute("user", user);
		return "/";
	}
	
}