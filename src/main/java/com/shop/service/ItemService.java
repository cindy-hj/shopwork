package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.model.Item;
import com.shop.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;

	public List<Item> findAll() {
		
		return itemRepository.findAll();
	}

	public Item save(Item item) {
		
		return itemRepository.save(item);
	}

	public List<Item> findByIdIn(List<Integer> itemIds) {
	
		return itemRepository.findByIdIn(itemIds);
	}
	
	
}
