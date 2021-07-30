package com.person.googlenotesclone.exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class TodoException {

	private final String message;
	private final HttpStatus status;
	private final ZonedDateTime timeStamp;
	
	
	public TodoException(String message, HttpStatus status, ZonedDateTime timeStamp) {
		this.message = message;
		this.status = status;
		this.timeStamp = timeStamp;
	}


	public String getMessage() {
		return message;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public ZonedDateTime getTimeStamp() {
		return timeStamp;
	}
	
	
	
	
	
}
