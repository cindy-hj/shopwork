package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByIdIn(List<Integer> itemIds);

}
