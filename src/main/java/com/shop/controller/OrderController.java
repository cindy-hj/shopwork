package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shop.model.Order;
import com.shop.model.request.OrderDto;
import com.shop.service.CartService;
import com.shop.service.JwtService;
import com.shop.service.OrderService;

import jakarta.transaction.Transactional;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/api/orders")
	public ResponseEntity getOrder(@CookieValue(value = "token", required = false)String token) {
		if(!jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		int memberId = jwtService.getId(token);
		List<Order> orders = orderService.findByMemberIdOrderByIdDesc(memberId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping("/api/orders")
	public ResponseEntity pushOrder(@RequestBody OrderDto dto, @CookieValue(value="token", required = false)String token) {
		if(!jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		int memberId = jwtService.getId(token);

		Order newOrder = new Order();
		
		newOrder.setMemberId(memberId);
		newOrder.setName(dto.getName());
		newOrder.setAddress(dto.getAddress());
		newOrder.setPayment(dto.getPayment());
		newOrder.setCardNumber(dto.getCardNumber());
		newOrder.setItems(dto.getItems());
		
		orderService.save(newOrder);
		cartService.deleteByMemberId(memberId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
