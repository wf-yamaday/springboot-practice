package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.Seller;
import com.example.repository.SellerRepository;

@Service(value="seller")
public class SellerUserDetailsService implements UserDetailsService{
	@Autowired
	SellerRepository sellerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
		Seller seller = sellerRepository.findOne(id);
		if(seller == null){
			throw new UsernameNotFoundException("The requested user is not found.");
		}
		return new SellerUserDetails(seller);
	}

}