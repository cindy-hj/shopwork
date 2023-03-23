package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.model.Cart;
import com.shop.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;

	public List<Cart> findByMemberId(int memberId) {
		return cartRepository.findByMemberId(memberId);
	}

	public Cart findByMemberIdAndItemId(int memberId, int itemId) {
		return cartRepository.findByMemberIdAndItemId(memberId, itemId);
	}

	public void save(Cart newCart) {
		cartRepository.save(newCart);
	}

	public void delete(Cart cart) {
		cartRepository.delete(cart);
		
	}

	public void deleteByMemberId(int memberId) {
		cartRepository.deleteByMemberId(memberId);
	}
}
