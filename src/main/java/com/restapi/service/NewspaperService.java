/**
 * 
 */
package com.restapi.service;

import java.util.List;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restapi.model.Newspaper;
import com.restapi.model.Post;
import com.restapi.repository.NewspaperRepository;
import com.restapi.repository.PostRepository;

/**
 * This service encapsulates all the actions related to a newspaper.
 * @author analia.hojman
 *
 */
@Component
public class NewspaperService {

	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@Autowired
	private PostRepository postRepository;

	/**
	 * Default constructor
	 */
	protected NewspaperService() {
	}

	/**
	 * Constructor
	 * @param newspaperRepository
	 * @param postRepository
	 */
	public NewspaperService(NewspaperRepository newspaperRepository, PostRepository postRepository) {
		this.newspaperRepository = newspaperRepository;
		this.postRepository=postRepository;
	}

	/**
	 * This service method creates a new newspaper based on the
	 * newspaperFromRequest argument.
	 * 
	 * @return the created newspaper
	 */
	public Newspaper create(Newspaper inputNewspaper) {
		
		Newspaper newspaper = getNewNewspaperBasedOnAttributes(inputNewspaper);
		this.newspaperRepository.save(newspaper);
		return newspaper;
	}

	/**
	 * This service method gets a Newspaper based on the newspaper id.
	 * 
	 * @return a newspaper
	 */
	public Newspaper read(Long newspaperId) {

		Newspaper newspaper = newspaperRepository.findById(newspaperId);
		return newspaper;

	}

	/**
	 * This service method gets all the newspapers or an empty list if nothing was found
	 * 
	 * @return newspaper list
	 */
	public List<Newspaper> readAll() {
		return this.newspaperRepository.findAll();
	}

	/**
	 * This service method updates a Newspaper based on the newspaper argument
	 * 
	 * @return the updated newspaper
	 */
	public Newspaper update(Newspaper newspaperWithUpdatedAttributes) {

		Newspaper updatedNewspaper = newspaperRepository.findById(newspaperWithUpdatedAttributes.getId());

		if (updatedNewspaper!= null){
			updatedNewspaper = newspaperWithUpdatedAttributes;
			this.newspaperRepository.save(updatedNewspaper);
		}
		return updatedNewspaper;
	}

	/**
	 * This service method removes a Newspaper based on the id argument
	 * 
	 * @return the removed newspaper
	 * @throws NotFoundException 
	 */
	public Newspaper remove(Long newspaperId) {

		Newspaper newspaperToBeRemoved = newspaperRepository.findById(newspaperId);

		if (newspaperToBeRemoved != null){
			this.newspaperRepository.delete(newspaperToBeRemoved);
		}
		
		return newspaperToBeRemoved;
	}
	
	/**
	 * This service method adds a new post to a newspaper
	 * 
	 * @return the newspaper with the new post
	 * @throws NotFoundException 
	 */
	public Newspaper addPost(Long newspaperId, Post post) {
		
		Newspaper newspaper = this.newspaperRepository.findById(newspaperId);
		
		if (newspaper != null ){
		
			//Saving the new post itself
			this.postRepository.save(post);
			
			//Adding the new post to the newspaper
			newspaper.addpost(post);
			
			//Saving the newspaper with the new post
			this.newspaperRepository.save(newspaper);
		}
		return newspaper;
	}
	
	/*
	 * TODO pending refactor: build a Newspaper Factory
	 */
	private Newspaper getNewNewspaperBasedOnAttributes(Newspaper inputNewspaper){
		
		Newspaper newspaper = new Newspaper();
		newspaper.setDate(inputNewspaper.getDate());
		newspaper.setName(inputNewspaper.getName());
		newspaper.setEditorialName(inputNewspaper.getEditorialName());
		
		return newspaper;
	}

}
