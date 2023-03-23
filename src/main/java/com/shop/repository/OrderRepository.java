package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByMemberIdOrderByIdDesc(int memberId);

}
