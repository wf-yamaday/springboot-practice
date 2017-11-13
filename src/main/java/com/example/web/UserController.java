package com.example.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.service.UserService;


@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userService;
	
	@ModelAttribute
	UserForm setUpForm() {
		return new UserForm();
		}
	@GetMapping
	String list(Model model){
		List<User> users = userService.findAll();
		model.addAttribute("users",users);
		return "list";
	}
	
	@PostMapping(path="create")
	String create(@Validated UserForm form, BindingResult result,Model model) {
		if(result.hasErrors()){
			return list(model);
		}
		User user = new User();
		BeanUtils.copyProperties(form,user);
		userService.create(user ,form.getPassword());
		return "redirect:/user";
	}
}
