package com.person.googlenotesclone.exceptions;

@SuppressWarnings("serial")
public class TodoRequestException extends RuntimeException{

	public TodoRequestException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public TodoRequestException(String message) {
		super(message);
		
	}
	

}
