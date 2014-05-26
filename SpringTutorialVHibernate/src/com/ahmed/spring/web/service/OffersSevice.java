package com.ahmed.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.ahmed.spring.web.dao.Offer;
import com.ahmed.spring.web.dao.OffersDao;
import com.ahmed.spring.web.test.tests.OfferDaoTests;

@Service("offersSevice")
public class OffersSevice {

	private OffersDao offersDAO;
	public List<Offer> getCurrent()
	{
		return offersDAO.getOffers();
	}
	@Autowired
	public void setOffersDAO(OffersDao offersDAO) {
		this.offersDAO = offersDAO;
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	public void create(Offer offer) {
		offersDAO.saveOrUpdate(offer);
		
	}
	public void throwTestExcption() {
		offersDAO.getOffer(999);
		
	}
	public boolean hasOffer(String name) {
		if(name == null){ return false;}
		List<Offer>offers = offersDAO.getOffers(name);
		if(offers.size() == 0)
		{
			return false;
		}
		return true;
	}
	
	public Offer getOffer(String username) {
		if(username== null)
		{
			return null;
		}
		List<Offer> offers = offersDAO.getOffers(username);
		if(offers.size() == 0){
			return null;
		}
		return offers.get(0);
	}
	public void saveOrUpdate(Offer offer) {
		
			offersDAO.saveOrUpdate(offer);
		
		
	}
	public void delete(int id) {
		offersDAO.delete(id);
		
	}
	
	
}
