package com.mymeal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mymeal.models.MenuItems;
import com.mymeal.services.MealListService;

@Controller
public class MealListController {
	private MealListService meallistservice;

	@Autowired
	public MealListController(MealListService meallistservice) {
		this.meallistservice = meallistservice;
	}

	@GetMapping("/menuList/listofitems")
	public String menuList(Model model) {
		model.addAttribute("menulist", meallistservice.getMenuItems());
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
			meallistservice.addMenuItem(item);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "addItem";
	}

}
