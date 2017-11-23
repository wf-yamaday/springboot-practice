package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="seller")
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
	@Id
	@Column(name="shop_id",nullable=false)
	private String shop_id;
	@Column(name="shop_name",nullable=false)
	private String shop_name;
	@Column(name="password",nullable=false)
	@JsonIgnore
	private String password;
}
