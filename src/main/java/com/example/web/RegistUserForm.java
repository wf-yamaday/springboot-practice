package com.example.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegistUserForm {
	@NotNull
	@Size(min=1,max=30)
	private String firstName;
	@NotNull
	@Size(min=1,max=30)
	private String lastName;
	@NotNull
	@Size(min=1)
	private String mail;
	@NotNull
	@Size(min=1,max=32)
	private String password;
}