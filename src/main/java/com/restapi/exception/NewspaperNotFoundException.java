package com.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Newspaper")  // Response Status: 404
public class NewspaperNotFoundException extends RuntimeException {
	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = -8790211652911971729L;

	public NewspaperNotFoundException(Long newspaperId) {
		super("The newspaper with id " + newspaperId + " doesn't exist");
	}
}
