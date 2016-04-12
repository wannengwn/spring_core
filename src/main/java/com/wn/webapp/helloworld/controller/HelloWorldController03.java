package com.wn.webapp.helloworld.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("helloWorldController03")
public class HelloWorldController03 {
	
	private Logger logger = LoggerFactory.getLogger(HelloWorldController03.class);
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String helloWorldController03(HttpServletRequest reqeust,Model model){
		logger.info("---------------helloWorldController03----list");
		return "/helloworld/helloWorld03";
	}
}
