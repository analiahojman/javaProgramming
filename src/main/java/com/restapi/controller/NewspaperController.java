package com.restapi.controller;

import java.util.List;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.exception.NewspaperNotFoundException;
import com.restapi.model.Newspaper;
import com.restapi.model.Post;
import com.restapi.service.NewspaperService;

/**
 * This controller handles all the HTTP request related to a Newspaper.
 * 
 * @author analia.hojman
 */
@RestController
@RequestMapping("/newspaper")
public class NewspaperController {

	@Autowired
	private NewspaperService newspaperService;

	/**
	 * Get a newspaper based on its id. 
	 */
	@RequestMapping(value = "/{newspaperId}", method = RequestMethod.GET)
	public ResponseEntity<Newspaper> read(@PathVariable Long newspaperId) {
		
		Newspaper newspaper = newspaperService.read(newspaperId);
		
		if ( newspaper == null) throw new NewspaperNotFoundException(newspaperId);
		
		return new ResponseEntity<Newspaper>(newspaper, HttpStatus.OK);
		
	}

	/**
	 * Get all the newspapers
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Newspaper> readAll() {
		
		List<Newspaper> newspaperList = this.newspaperService.readAll();
		return newspaperList;
	}

	/**
	 * Creates a new newspaper based on the request body attributes.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Newspaper> create(
			@RequestBody Newspaper newspaperFromRequest) {

		Newspaper newspaper = newspaperService.create(newspaperFromRequest);
		return new ResponseEntity<Newspaper>(newspaper, HttpStatus.CREATED);
	}

	/**
	 * Update a newspaper based on the one that has been sent as a request body
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Newspaper> update(@RequestBody Newspaper newspaperFromRequest) {

		Newspaper updatedNewspaper = this.newspaperService.update(newspaperFromRequest);
		
		if ( updatedNewspaper == null) throw new NewspaperNotFoundException(newspaperFromRequest.getId());
		
		return new ResponseEntity<Newspaper>(updatedNewspaper, HttpStatus.OK);
		
	}

	/**
	 * Delete a newspaper based on its id
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/{newspaperId}", method = RequestMethod.DELETE)
	public ResponseEntity<Newspaper> remove(@PathVariable Long newspaperId)   {
		
		Newspaper removedNewspaper = this.newspaperService.remove(newspaperId);

		if ( removedNewspaper == null) throw new NewspaperNotFoundException(newspaperId);

		return new ResponseEntity<Newspaper>(removedNewspaper, HttpStatus.OK);
	}

	/**
	 * Add a post to the newspaper
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/{newspaperId}/post/", method = RequestMethod.POST)
	public ResponseEntity<Newspaper> addPost(@PathVariable Long newspaperId, @RequestBody Post post)  {

		Newspaper newspaper = this.newspaperService.addPost(newspaperId, post);
		
		if ( newspaper == null) throw new NewspaperNotFoundException(newspaperId);

		return new ResponseEntity<Newspaper>(newspaper, HttpStatus.CREATED);
		
	}

}
