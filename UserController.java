package com.userManagement.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.userManagement.configproperties.AppProperties;
import com.userManagement.constant.UserConstant;
import com.userManagement.model.User;
import com.userManagement.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	
	@Autowired
	private AppProperties appProperties;
	
	@GetMapping("/user")
	public String loadUserDetails(Model model)
	{
		User user = new User();
		
		
		model.addAttribute("user", user);
		
		return "userinfo";
	}

	
	@PostMapping("/saveUser")
	public String saveUser(User user ,Model model)
	{
		Map<String, String> messages = appProperties.getMessages();
		boolean saveUser = service.saveUser(user);
		
		if(saveUser)
		{
			model.addAttribute("SUCCESS", messages.get("saveSuccess"));
		}
		else
		{
			model.addAttribute("saveFail", messages.get("saveFail"));
			
		}
			
		return "userinfo";
		
		
	}
	
	@GetMapping("/viewAllUser")
	public String viewAllUser(Model model)
	{
		List<User> allUser = service.getAllUser();
		      
		List<User> collect = allUser.stream().filter(
				newuser -> newuser.getIsActive()==UserConstant.isActiveTrue)
		        .collect(Collectors.toList());
		
		model.addAttribute("contact", collect);
		return "contact";
	}
	
}

