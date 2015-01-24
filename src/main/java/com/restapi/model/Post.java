package com.restapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class represents a post entity
 * @author analia.hojman
 */
@Entity
public class Post {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String author;
	private String title;
	private String body;
	
	/**
	 * Default constructor used by JPA
	 */
	public Post(){}
	
	/**
	 * Post Constructor
	 * @param author
	 * @param title
	 * @param body
	 * @param informative
	 */
	public Post(String author, String title, String body) {
		super();
		this.author = author;
		this.title = title;
		this.body = body;
	}

	/**
	 * Getters and setters
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
