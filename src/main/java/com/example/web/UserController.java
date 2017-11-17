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
@RequestMapping("/")//このコントローラ全体が動作するパス
public class UserController {
	@Autowired
	UserService userService;
	
	//新規ユーザー登録フォーム
	@ModelAttribute
	RegistUserForm registUserForm(){
		return new RegistUserForm();
	}
	@GetMapping(path="registUserForm")
	String regist(Model model){
		return "registUserForm";
	}
	
	@GetMapping("/top")
	String top(){
		return "top";
	}
	
	/*String list(Model model){
		List<User> users = userService.findAll();
		model.addAllAttributes(users);
		return "user/list";
	}*/
	
	//新規登録
	@PostMapping(path="create")//新規登録のpostリクエストを受けるパス
	String create(@Validated RegistUserForm form , BindingResult result , Model model){
		if(result.hasErrors()){ //エラーがおきたら返す場所
			return "/registUserForm";
		}
		User user = new User();
		BeanUtils.copyProperties(form, user);
		userService.create(user, user.getPassword());
		return "redirect:/loginForm"; //成功したら返す場所
	}
	
}
