package com.wn.webapp.index.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wn.webapp.platform.account.entity.User;
import com.wn.webapp.platform.account.service.UserService;


@Controller
public class indexController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model) throws Exception{
		String loginName = (String)SecurityUtils.getSubject().getPrincipal();;
        User user = userService.findUserByLoginName(loginName);
        model.addAttribute("user", user);
		return "/index";
	}
	
	@RequestMapping(value="/layout_ajax_content_1",method = RequestMethod.GET)
	public String layout_ajax_content_1(HttpServletRequest request,Model model) throws Exception{
		
		return "/index/layout_ajax_content_1";
	}
}
