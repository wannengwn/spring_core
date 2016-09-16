package com.wn.webapp.platform.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wn.webapp.platform.account.dao.MenuDao;
import com.wn.webapp.platform.account.entity.Menu;

public class MenuService {

	@Autowired
	private MenuDao menuDao;

	public List<Menu> queryVisible() {
		return menuDao.findAllVisible();
	}

}
