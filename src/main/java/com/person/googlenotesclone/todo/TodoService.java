package com.person.googlenotesclone.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.person.googlenotesclone.exceptions.TodoRequestException;

import java.util.*;

import javax.transaction.Transactional;

@Service
public class TodoService {

	private final TodoRepository todoRepository;
	
	@Autowired
	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<Todo> getTodos() {
		return todoRepository.findAll();
	}

	public Todo getTodo(Long todoId) {
		Optional<Todo> todo = todoRepository.findById(todoId);
		
		if(!todo.isPresent())
		{
			throw new TodoRequestException("ID Not Found");
		}
		return todo.get();
	}

	public Todo newTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	public Todo deleteTodo(Long todoId) {
		Optional<Todo> todo = todoRepository.findById(todoId);
		
		if(!todo.isPresent())
		{
			throw new TodoRequestException("ID Not Found");
		}
		todoRepository.deleteById(todoId);
		return todo.get();
		
	}

	@Transactional
	public void updateTodo(Long todoId, String title, String description)  {
		Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoRequestException("ID Not Found"));
		todo.setTitle(title);
		todo.setDescription(description);
	}
	
	
}
