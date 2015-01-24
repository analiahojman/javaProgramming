package com.restapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * This class represents an Informative entity.
 * @author analia.hojman
 */
@Entity
public abstract class Informative {


    @Id
    @GeneratedValue
	private Long id;
    
	@OneToMany
	private List<Post> posts = new ArrayList<Post>();
	
	private String name;
	private String editorialName;
	private Date date;

	/**
	 * Default constructor used by JPA
	 */
	public Informative(){}
	
	/**
	 * Informative constructor
	 * @param name
	 * @param editorialName
	 * @param date
	 * @param posts
	 */
	public Informative(String name, String editorialName, Date date,
			List<Post> posts) {
		super();
		this.name = name;
		this.editorialName = editorialName;
		this.date = date;
		this.posts = posts;
	}
	
	/**
	 * This method simulates how an informative is printed.
	 * Each concrete informative must implement its behavior.
	 */
	public abstract void print();

	/**
	 * Getters and Setters
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEditorialName() {
		return editorialName;
	}

	public void setEditorialName(String editorialName) {
		this.editorialName = editorialName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public void addpost(Post post){
		this.posts.add(post);
	}
	
}
