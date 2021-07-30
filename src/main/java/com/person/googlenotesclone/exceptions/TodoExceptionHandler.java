package com.person.googlenotesclone.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoExceptionHandler {

	@ExceptionHandler(value = {TodoRequestException.class})
	public ResponseEntity<Object> handleTodoException(TodoRequestException e)
	{
		//1. Create payload containing exception details
		
		TodoException todoException = new TodoException
				(
						e.getMessage(), 
						HttpStatus.BAD_REQUEST, 
						ZonedDateTime.now(ZoneId.of("Z"))
				);
		//2. Return response entity
		return new ResponseEntity<>(todoException,HttpStatus.BAD_REQUEST);
	}
}
