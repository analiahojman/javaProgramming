package com.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restapi.model.Informative;
import com.restapi.repository.InformativeRepository;

/**
 * This service encapsulates all the actions related to an informative.
 * @author analia.hojman
 *
 */
@Component
public class InformativeService {

	@Autowired
	InformativeRepository informativeRepository;

	/**
	 * Default constructor
	 */
	public InformativeService() {}
	
	/**
	 * @param informativeRepository
	 */
	public InformativeService(InformativeRepository informativeRepository) {
		super();
		this.informativeRepository = informativeRepository;
	}
	
	/**
	 * This service method prints all the informatives
	 * @return list of the printed informatives
	 */
	public List<Informative> printAll(){
		List<Informative> informatives = this.informativeRepository.findAll();
		for (Informative currentInformative : informatives){
			currentInformative.print();
		}
		return informatives;
	}
	
}
