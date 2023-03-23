package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.model.Member;
import com.shop.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	public Member findByEmailAndPassword(String email, String password) {

		return memberRepository.findByEmailAndPassword(email, password);
	}
}
