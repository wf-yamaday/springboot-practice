package com.example.service;

import org.springframework.security.core.authority.AuthorityUtils;

import com.example.domain.Seller;

import lombok.Data;

@Data
public class SellerUserDetails extends org.springframework.security.core.userdetails.User{
	private final Seller seller;
	
	public SellerUserDetails(Seller seller){
		super(seller.getShop_id(), seller.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.seller = seller;
	}
}
