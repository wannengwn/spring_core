package com.wn.webapp.platform.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wn.webapp.platform.account.dao.MenuDao;
import com.wn.webapp.platform.account.entity.Menu;

@Component
@Transactional
public class MenuService {

	@Autowired
	private MenuDao menuDao;

	public List<Menu> queryVisible() {
		List<Menu> menuList = menuDao.findAllVisible();
	    return menuList;
	}
	public List<Menu> queryVisible(Boolean isRoot) {
		List<Menu> menuList = menuDao.findAllVisible(isRoot);
	    return menuList;
	}
}
