package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Seller;

public interface SellerRepository extends JpaRepository<Seller, String>{

}
