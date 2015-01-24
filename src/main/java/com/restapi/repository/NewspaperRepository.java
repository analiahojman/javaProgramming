package com.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.model.Newspaper;

/**
 * Newspaper repository to perform newspaper queries on database. 
 * @author analia.hojman
 */
@Repository
public interface NewspaperRepository extends JpaRepository<Newspaper, Long> {

	public Newspaper findById(Long id);

	public List<Newspaper> findAll();
	
	public Newspaper findByName(String name);


}
