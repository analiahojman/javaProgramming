package com.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.model.DigitalNewspaper;

/**
 * Digital Newspaper repository to perform newspaper queries on database.
 * @author analia.hojman
 */
@Repository
public interface DigitalNewspaperRepository extends JpaRepository<DigitalNewspaper, Long> {
	
	public DigitalNewspaper findById(Long id);

	public List<DigitalNewspaper> findAll();

}
