/**
 * 
 */
package com.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.model.Post;

/**
 * Post repository to perform post queries on database. 
 * @author analia.hojman
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	public Post findById(Long id);
	
	public List<Post> findAll();
	
	public Post findByTitle(String title);

}
