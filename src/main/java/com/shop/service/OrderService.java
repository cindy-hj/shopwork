package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.model.Order;
import com.shop.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public List<Order> findByMemberIdOrderByIdDesc(int memberId) {
		return orderRepository.findByMemberIdOrderByIdDesc(memberId);
	}

	public void save(Order newOrder) {
		orderRepository.save(newOrder);
	}
}
