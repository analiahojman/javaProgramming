package com.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.model.DigitalNewspaper;
import com.restapi.service.DigitalNewspaperService;

/**
 * This controller handles all the HTTP request related to a Digital Newspaper.
 * 
 * @author analia.hojman
 */
@RestController
@RequestMapping("/digitalNewspaper")
public class DigitalNewspaperController {

	@Autowired
	private DigitalNewspaperService digitalNewspaperService;
	
	/**
	 * Creates a new digital newspaper based on the request body attributes.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DigitalNewspaper> create(
			@RequestBody DigitalNewspaper digitalNewspaperFromRequest) {

		DigitalNewspaper digitalNewspaper = this.digitalNewspaperService.create(digitalNewspaperFromRequest);
		return new ResponseEntity<DigitalNewspaper>(digitalNewspaper, HttpStatus.CREATED);
	}


}
