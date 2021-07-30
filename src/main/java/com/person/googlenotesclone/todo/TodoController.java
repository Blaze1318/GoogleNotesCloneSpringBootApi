package com.person.googlenotesclone.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
@RequestMapping(path = "api/v1/todo")
@CrossOrigin(origins = "*")
public class TodoController {

	private final TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping
	public List<Todo> getTodos()
	{
		return todoService.getTodos();
	}
	
	@GetMapping(path ="{todoId}")
	public Optional<Todo> getTodo(@PathVariable("todoId") Long todoId) 
	{
		return todoService.getTodo(todoId);
	}
	
	@PostMapping()
	public void newTodo(@RequestBody Todo todo)
	{
		todoService.newTodo(todo);
	}
	
	@DeleteMapping(path = "{todoId}")
	public void deleteTodo(@PathVariable("todoId") Long todoId)
	{
		todoService.deleteTodo(todoId);
	}
	
	@PutMapping(path = "{todoId}")
	public void updateTodo(@PathVariable("todoId") Long todoId,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String description)
	{
		todoService.updateTodo(todoId,title,description);
	}
}
