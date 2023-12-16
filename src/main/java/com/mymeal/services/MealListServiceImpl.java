package com.mymeal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymeal.daos.MenuItemsDao;
import com.mymeal.models.MenuItems;

@Service
public class MealListServiceImpl implements MealListService {

	private MenuItemsDao menuItemsDao;

	@Autowired
	public MealListServiceImpl(MenuItemsDao menuItemsDao) {
		this.menuItemsDao = menuItemsDao;
	}

	public List<MenuItems> getMenuItems() {
		return menuItemsDao.getAllItems();

	}

	public void addMenuItem(MenuItems item) {
		// TODO Auto-generated method stub
		menuItemsDao.addMenuItem(item);

	}

}
