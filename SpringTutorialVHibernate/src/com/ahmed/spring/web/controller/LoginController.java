package com.ahmed.spring.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ahmed.spring.web.dao.FormValidationGroup;
import com.ahmed.spring.web.dao.Offer;
import com.ahmed.spring.web.dao.User;
import com.ahmed.spring.web.service.OffersSevice;
import com.ahmed.spring.web.service.UsersSevice;

@Controller
public class LoginController {
	
	private UsersSevice usersSevice;
	@RequestMapping("/login")
	public String showLogin()
	{
		return "login";
	}
	
	@RequestMapping("/denied")
	public String showDenied()
	{
		return "denied";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
	List<User> users = usersSevice.getAllUsers();
		
		model.addAttribute("users", users);
		return "admin";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedout()
	{
		return "loggedout";
	}
	
	@RequestMapping("/newaccount")
	public String showNewaccount(Model model)
	{
		model.addAttribute("user",new User());
		return "newaccount";
	}
	
	
	
	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createaccount(@Validated(FormValidationGroup.class) User user, BindingResult result) {
		if (result.hasErrors()) {
			// System.out.println("form does not validate");
			// List<ObjectError> errors = result.getAllErrors();
			// for (ObjectError error : errors) {
			// System.out.println(error.getDefaultMessage());
			// }

			return "newaccount";
		}
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		if(usersSevice.exists(user.getUsername()))
		{
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		try {
			usersSevice.create(user);
		} catch (DuplicateKeyException e) {
			
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
	
		return "accountcreated";
	}
	@Autowired
	public void setUsersSevice(UsersSevice usersSevice) {
		this.usersSevice = usersSevice;
	}

	
	
	
}
