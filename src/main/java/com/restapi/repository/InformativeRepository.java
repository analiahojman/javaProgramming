package com.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.restapi.model.Informative;

/**
 * Informative repository to perform informative queries on database.
 * @author analia.hojman
 */
@Repository
public interface InformativeRepository extends JpaRepository<Informative, Long> {

	public Informative findById(Long id);

	public List<Informative> findAll();

}
