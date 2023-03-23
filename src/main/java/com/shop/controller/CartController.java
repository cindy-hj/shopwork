package com.shop.controller;

import java.lang.module.ResolutionException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shop.model.Cart;
import com.shop.model.Item;
import com.shop.service.CartService;
import com.shop.service.ItemService;
import com.shop.service.JwtService;

@RestController
public class CartController {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/api/cart/items")
	public ResponseEntity getCartItems(@CookieValue(value="token", required=false)String token) {
		if(!jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		int memberId = jwtService.getId(token);
		List<Cart> carts = cartService.findByMemberId(memberId);
		List<Integer> itemIds = carts.stream().map(Cart::getItemId).toList();
		List<Item> items = itemService.findByIdIn(itemIds);
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@PostMapping("/api/cart/items/{itemId}")
	public ResponseEntity pushCartItem(
			@PathVariable("itemId")int itemId, 
			@CookieValue(value="token",required=false)String token
		) {
		if(!jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		int memberId = jwtService.getId(token);
		Cart cart = cartService.findByMemberIdAndItemId(memberId,itemId);
		
		if(cart == null) {
			Cart newCart = new Cart();
			newCart.setMemberId(memberId);
			newCart.setItemId(itemId);
			cartService.save(newCart);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/api/cart/items/{itemId}")
	public ResponseEntity removeCartItem(
			@PathVariable("itemId")int itemId, 
			@CookieValue(value = "token",required = false)String token
		) {
		if(!jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		int memberId = jwtService.getId(token);
		Cart cart = cartService.findByMemberIdAndItemId(memberId, itemId);
		
		cartService.delete(cart);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
