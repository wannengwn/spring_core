package com.wn.webapp.helloworld.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("helloWorldController02")
public class HelloWorldController02 {
	
	private Logger logger = LoggerFactory.getLogger(HelloWorldController02.class);
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String login(HttpServletRequest reqeust,Model model){
		logger.info("---------------helloWorldController02----list");
		return "/helloworld/helloWorld02";
	}
}
