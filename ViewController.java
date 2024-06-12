package com.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.userManagement.model.User;
import com.userManagement.service.UserService;

@Controller
public class ViewController {

	@Autowired
	private UserService service;
	
	
	
	@GetMapping("/edit")
	public String editUser(@RequestParam("userId") Integer userId ,Model model)
	{
		
		User user = service.getUser(userId);
		model.addAttribute("user", user);
		return "userinfo";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId") Integer userId ,Model model)
	{
		service.deleteUser(userId);
		return "redirect:viewAllUser";
	}
	
}
