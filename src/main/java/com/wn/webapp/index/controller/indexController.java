package com.wn.webapp.index.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wn.webapp.domain.PageSupport;
import com.wn.webapp.platform.account.entity.Menu;
import com.wn.webapp.platform.account.entity.User;
import com.wn.webapp.platform.account.service.MenuService;
import com.wn.webapp.platform.account.service.UserService;


@Controller
public class indexController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model) {
		try {
			String loginName = (String)SecurityUtils.getSubject().getPrincipal();;
			User user = userService.findUserByLoginName(loginName);
			List<Menu> treeNodeList = menuService.queryVisible(Boolean.TRUE);
			model.addAttribute("moduleTree", treeNodeList);
			model.addAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/index";
	}
	
	@RequestMapping(value="/layout_ajax_content_1",method = RequestMethod.GET)
	public String layout_ajax_content_1(HttpServletRequest request,Model model,@PageableDefault(2) Pageable pageable) {
		try {
			
			PageSupport pageSupport = new PageSupport(pageable, new Sort(Sort.Direction.DESC,"createTime"));
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Page<User> pageInfo = userService.queryPageByMap(paramMap, pageSupport);
			model.addAttribute("pageInfo",pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/index/indexList";
	}
}
