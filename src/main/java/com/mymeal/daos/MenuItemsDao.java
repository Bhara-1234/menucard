package com.mymeal.daos;

import java.util.List;

import com.mymeal.models.MenuItems;

public interface MenuItemsDao {

	public List<MenuItems> getAllItems();

	public void addMenuItem(MenuItems item);

}
