package com.wn.webapp.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wn.webapp.user.entity.User;
import com.wn.webapp.user.service.UserService;

@Controller
public class loginController {
	
	private Logger logger = LoggerFactory.getLogger(loginController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(HttpServletRequest request){
		SecurityUtils.getSubject().logout();
		return "/login";
	}
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String  login(HttpServletRequest request,Model model){
		//管理员 admin Mvtech123
		Object obj = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String errorClassName = (String) obj;
        if(UnknownAccountException.class.getName().equals(errorClassName)) {
            model.addAttribute("errorMsg", "账号不存在");
        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            model.addAttribute("errorMsg", "密码错误");
        } else if (LockedAccountException.class.getName().equals(errorClassName)) {
            model.addAttribute("errorMsg", "帐号被锁定");
        } else if(ExpiredCredentialsException.class.getName().equals(errorClassName)) {
            model.addAttribute("errorMsg", "凭证过期");
        }else if(ExcessiveAttemptsException.class.getName().equals(errorClassName)) {
            model.addAttribute("errorMsg", "登录次数过多，稍后再登录(一小时后)");
        }else if(DisabledAccountException.class.getName().equals(errorClassName)) {
            model.addAttribute("errorMsg", "帐号被禁用");
        } else if(errorClassName != null) {
            model.addAttribute("errorMsg", "其他异常");
        }
        logger.info("登录系统错误,错误异常："+errorClassName);
		return "/login";
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public void logout(HttpServletRequest request,Model model){
		logger.info("退出登录");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String register(HttpServletRequest request,Model model){
		
		return "/register";
	}
}
