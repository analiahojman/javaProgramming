package com.restapi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

/**
 * This class represents a Digital Newspaper entity
 * @author analia.hojman
 */
@Entity
public class DigitalNewspaper extends Informative {
	
	/**
	 * Default constructor used by JPA
	 */
	public DigitalNewspaper() {}

	/**
	 * DigitalNewspaper Constructor
	 * @param name
	 * @param editorialName
	 * @param date
	 * @param posts
	 */
	public DigitalNewspaper(String name, String editorialName, Date date,
			List<Post> posts) {
		super(name, editorialName, date, posts);
	}

	/**
	 * This method simulates how a digital newspaper is printed
	 */
	@Override
	public void print() {
		System.out.println("--------------------------");
		System.out.println("I am " + this.getName() + ", a Digital Newspaper");
		System.out.println("--------------------------");
	}

}
