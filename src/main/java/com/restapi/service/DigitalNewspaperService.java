package com.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restapi.model.DigitalNewspaper;
import com.restapi.model.Post;
import com.restapi.repository.DigitalNewspaperRepository;
import com.restapi.repository.PostRepository;

/**
 * This service encapsulates all the actions related to a digital newspaper.
 * @author analia.hojman
 *
 */
@Component
public class DigitalNewspaperService {

	@Autowired
	private DigitalNewspaperRepository digitalNewspaperRepository;
	
	@Autowired
	private PostRepository postRepository;

	/**
	 * Default constructor
	 */
	public DigitalNewspaperService() {}
	
	/**
	 * DigitalNewspaperService Constructor
	 * @param newspaperRepository
	 */
	public DigitalNewspaperService(DigitalNewspaperRepository digitalNewspaperRepository, PostRepository postRepository) {
		this.digitalNewspaperRepository = digitalNewspaperRepository;
		this.postRepository = postRepository;
	}

	/**
	 * This service method creates a new DigitalNewspaper based on the
	 * DigitalNewspaperFromRequest argument.
	 * 
	 * @return the created DigitalNewspaper
	 */
	public DigitalNewspaper create(DigitalNewspaper digitalNewspaperInput) {

		DigitalNewspaper digitalNewspaper = getNewDigitalNewspaperBasedOnAttributes(digitalNewspaperInput);

		this.digitalNewspaperRepository.save(digitalNewspaper);
		return digitalNewspaper;
	}
	
	/**
	 * This service method gets a digital newspaper based on the digital newspaper id.
	 * 
	 * @return a digital newspaper 
	 */
	public DigitalNewspaper read(Long digitalNewspaperId)  {

		DigitalNewspaper digitalNewspaper = this.digitalNewspaperRepository.findById(digitalNewspaperId);			
		return digitalNewspaper;

	}

	/**
	 * This service method gets all the digital newspapers or an empty list if nothing was found
	 * 
	 * @return digital newspaper list
	 */
	public List<DigitalNewspaper> readAll() {
		return this.digitalNewspaperRepository.findAll();
	}

	/**
	 * This service method updates a digital newspaper based on the digital newspaper argument
	 * 
	 * @return the updated newspaper
	 */
	public DigitalNewspaper update(DigitalNewspaper digitalNewspaperWithUpdatedAttributes)
			 {

		DigitalNewspaper updatedDigitalNewspaper = this.digitalNewspaperRepository
				.findById(digitalNewspaperWithUpdatedAttributes.getId());

		if (updatedDigitalNewspaper != null){
			updatedDigitalNewspaper = digitalNewspaperWithUpdatedAttributes;
			this.digitalNewspaperRepository.save(updatedDigitalNewspaper);
		}
		
		return updatedDigitalNewspaper;
	}

	/**
	 * This service method removes a digital newspaper based on the id argument
	 * 
	 * @return the removed digital newspaper
	 * @ 
	 */
	public DigitalNewspaper remove(Long digitalNewspaperId)  {

		DigitalNewspaper digitalNewspaperToBeRemoved = this.digitalNewspaperRepository
				.findById(digitalNewspaperId);

		if (digitalNewspaperToBeRemoved != null){
			this.digitalNewspaperRepository.delete(digitalNewspaperToBeRemoved);
		}
		return digitalNewspaperToBeRemoved;
	}
	
	/**
	 * This service method adds a new post to a digital newspaper
	 * 
	 * @return the digital newspaper with the new post
	 */
	public DigitalNewspaper addPost(Long digitalNewspaperId, Post post) {
		
		DigitalNewspaper digitalNewspaper = this.digitalNewspaperRepository.findById(digitalNewspaperId);
		
		if (digitalNewspaper != null ) { 
		
			//Saving the new post itself
			this.postRepository.save(post);
			
			//Adding the new post to the newspaper
			digitalNewspaper.addpost(post);
			
			//Saving the newspaper with the new post
			this.digitalNewspaperRepository.save(digitalNewspaper);
		}
		return digitalNewspaper;
	}

	/*
	 * TODO pending refactor: build a Digital Newspaper Factory
	 */
	private DigitalNewspaper getNewDigitalNewspaperBasedOnAttributes(DigitalNewspaper inputDigitalNewspaper){
		
		DigitalNewspaper digitalNewspaper = new DigitalNewspaper();
		digitalNewspaper.setDate(inputDigitalNewspaper.getDate());
		digitalNewspaper.setName(inputDigitalNewspaper.getName());
		digitalNewspaper.setEditorialName(inputDigitalNewspaper.getEditorialName());
		
		return digitalNewspaper;
	}

	
}
