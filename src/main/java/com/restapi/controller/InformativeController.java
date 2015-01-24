package com.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.model.Informative;
import com.restapi.repository.InformativeRepository;

/**
 * This controller handles all the HTTP request related to an informative.
 * 
 * @author analia.hojman
 */
@RestController
@RequestMapping("/informative")
public class InformativeController {

	@Autowired
	InformativeRepository informativeRepository;
	
	/**
	 * Print all the informatives
	 * @return list of the printed informatives
	 */
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public List<Informative> printAll() {
		
		List<Informative> informatives = this.informativeRepository.findAll();
		for (Informative currentInformative : informatives){
			currentInformative.print();
		}
		return informatives;
	}

}
