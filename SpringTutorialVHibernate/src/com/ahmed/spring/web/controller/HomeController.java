package com.ahmed.spring.web.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ahmed.spring.web.dao.Offer;
import com.ahmed.spring.web.service.OffersSevice;

@Controller
public class HomeController {

	@Autowired
	private OffersSevice offersSevice;
	private static Logger logger= Logger.getLogger(HomeController.class);
	@RequestMapping("/")
	public String showHome(Model model , Principal principal) {
		//logger.info("show home page");
		
		List<Offer> offers = offersSevice.getCurrent();
		model.addAttribute("offers", offers);
		
		boolean hasOffers = false;
		if(principal != null)
		{
			hasOffers = offersSevice.hasOffer(principal.getName());
		}
		
		model.addAttribute("hasOffers", hasOffers);
		return "home";
	}

	

}
