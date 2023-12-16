package com.mymeal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mymeal.models.MenuItems;
import com.mymeal.services.MealListServiceImpl;

@Controller
public class MealListController {
	private MealListServiceImpl meallistservicimpl;

	@Autowired
	public MealListController(MealListServiceImpl meallistservicimpl) {
		this.meallistservicimpl = meallistservicimpl;
	}

	@GetMapping("/menuList/listofitems")
	public String menuList(Model model) {
		model.addAttribute("menulist", meallistservicimpl.getMenuItems());
		return "start";
	}
	@GetMapping("/add")
	public String addList() {
		
		return "addItem";
	}
	
	 @PostMapping("/addItem")
	    public String addItem(@RequestBody MenuItems item) {
	        try {
	            // Process the item (save to the database, etc.)
	        	meallistservicimpl.addMenuItem(item);

	        } catch (Exception e) {
	            e.printStackTrace();

	        }
	        return "addItem";
}
	 
}
