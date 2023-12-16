package com.mymeal.daos;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mymeal.models.MenuItems;

@Repository
public class MenuItemsDaoImpl implements MenuItemsDao {

	private final JdbcTemplate jdbcTemplate;
	LocalTime currentTime = LocalTime.now();
	LocalTime morning = LocalTime.of(12, 00, 0);
	LocalTime afternoon = LocalTime.of(4, 00, 0);
	LocalTime evening = LocalTime.of(6, 30, 0);

	// Constructor
	@Autowired
	public MenuItemsDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Method to retrieve all items from the Items table
	public List<MenuItems> getAllItems() {
		if (currentTime.isBefore(morning)) {

			String sql = "SELECT * FROM Items where timing = 'Morning' ";
			// Using BeanPropertyRowMapper to automatically map rows to Item objects
			List<MenuItems> items = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MenuItems.class));
			return items;
		} else if (currentTime.isAfter(morning) && currentTime.isBefore(afternoon)) {
			String sql = "SELECT * FROM Items where timing = 'Afternoon' ";
			List<MenuItems> items = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MenuItems.class));
			return items;
		} else if (currentTime.isAfter(afternoon) && currentTime.isBefore(evening)) {
			String sql = "SELECT * FROM Items where timing = 'Evening' ";
			List<MenuItems> items = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MenuItems.class));
			return items;

		}
		return null;

	}

	@Override
	public void addMenuItem(MenuItems item) {
		// TODO Auto-generated method stub
		 String sql = "INSERT INTO items (itemname, price, quantity, timing, category) VALUES (?, ?, ?, ?, ?)";
	        
	        jdbcTemplate.update(sql,
	                item.getItemName(),
	                item.getPrice(),
	                item.getQuantity(),
	                item.getTiming(),
	                item.getCategory()
	        );
	}

	// Other methods for CRUD operations can be added as needed
}
