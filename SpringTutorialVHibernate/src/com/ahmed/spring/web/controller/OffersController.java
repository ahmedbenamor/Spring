package com.ahmed.spring.web.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ahmed.spring.web.dao.FormValidationGroup;
import com.ahmed.spring.web.dao.Offer;
import com.ahmed.spring.web.service.OffersSevice;
import com.ahmed.spring.web.service.UsersSevice;

@Controller
public class OffersController {

	private OffersSevice offersSevice;
	

	/*
	 * @RequestMapping("/") public ModelAndView showHome() { ModelAndView mv =
	 * new ModelAndView("home"); Map<String,Object> model = mv.getModel();
	 * model.put("name", "<b>River</b>"); return mv; }
	 */

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {
		System.out.println("Id is " + id);
		return "offers";
	}

	@RequestMapping("/offers")
	public String showOffers(Model model) {
		//offersSevice.throwTestExcption();
		List<Offer> offers = offersSevice.getCurrent();
		model.addAttribute("offers", offers);
		return "offers";
	}

	/*
	 * @ExceptionHandler(DataAccessException.class) public String
	 * handleDatabaseException(DataAccessException ex) { return "error"; }
	 */
	@RequestMapping("/createoffer")
	public String createOffer(Model model ,Principal principal) {
		Offer offer = null;
		if(principal != null)
		{
			String username = principal.getName();
			 offer = offersSevice.getOffer(username);
		}
		if(offer == null)
		{
			offer = new Offer();
		}
		model.addAttribute("offer", offer);

		return "createoffer";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Validated(value=FormValidationGroup.class) Offer offer, BindingResult result, Principal principal, @RequestParam(value="delete", required= false) String delete) {
		if (result.hasErrors()) {
			// System.out.println("form does not validate");
			// List<ObjectError> errors = result.getAllErrors();
			// for (ObjectError error : errors) {
			// System.out.println(error.getDefaultMessage());
			// }

			return "createoffer";
			
		}
		if(delete ==  null)
		{
			String username = principal.getName();
			offer.getUser().setUsername(username);
			offersSevice.saveOrUpdate(offer);
			return "offercreated";
		}
		else
		{
			offersSevice.delete(offer.getId());
			return "offerdeleted";
		}
		
		//offersSevice.create(offer);
		
	}

	@Autowired
	public void setOffersSevice(OffersSevice offersSevice) {
		this.offersSevice = offersSevice;
	}

}
