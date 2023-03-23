package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.model.Item;
import com.shop.service.ItemService;

@RestController
public class ItemController {
	@Autowired
	private ItemService itemService;

	@GetMapping("/api/items")
	public List<Item> getItems() {
		List<Item> items = itemService.findAll();
		return items;
	}
	
	@PostMapping("/api/additem")
	public Item addItem(@RequestBody Item item) {
		Item addItem = new Item();
		addItem.setName(item.getName());
		addItem.setImgPath(item.getImgPath());
		addItem.setDiscountPer(item.getDiscountPer());
		addItem.setPrice(item.getPrice());
		
		return itemService.save(item);
	}
	
}
