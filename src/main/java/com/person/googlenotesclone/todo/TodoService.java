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

	public Optional<Todo> getTodo(Long todoId) {
		boolean exist = todoRepository.existsById(todoId);
		
		if(!exist)
		{
			throw new TodoRequestException("ID Not Found");
		}
		return todoRepository.findById(todoId);
	}

	public Todo newTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	public void deleteTodo(Long todoId) {
		boolean exist = todoRepository.existsById(todoId);
		
		if(!exist)
		{
			throw new TodoRequestException("ID Not Found");
		}
		todoRepository.deleteById(todoId);
		
	}

	@Transactional
	public void updateTodo(Long todoId, String title, String description)  {
		Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoRequestException("ID Not Found"));
		todo.setTitle(title);
		todo.setDescription(description);
	}
	
	
}
