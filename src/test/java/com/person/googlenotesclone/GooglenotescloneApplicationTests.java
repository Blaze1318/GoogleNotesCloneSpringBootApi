package com.person.googlenotesclone;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import com.person.googlenotesclone.todo.Todo;
import com.person.googlenotesclone.todo.TodoRepository;
import com.person.googlenotesclone.todo.TodoService;



@ExtendWith(MockitoExtension.class)
@SpringBootTest
class GooglenotescloneApplicationTests {

	@Autowired
	private TodoService todoService;
	
	@MockBean
	private TodoRepository todoRepository;
	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getTodosTest()
	{
		when(todoRepository.findAll()).thenReturn(Stream.of(new Todo("Hey there","test"),
				new Todo("Hey there","test2")).collect(Collectors.toList()));
		
		assertEquals(2, todoService.getTodos().size());
	}
	
	@Test
	public void getTodoTest()
	{
		Todo todo = new Todo(3L,"Single Test","test3");
		Long id = todo.getId();
		
		when(todoRepository.findById(id)).thenReturn(Optional.of(todo));
		
		//Optional<Todo> returnedTodo = todoService.getTodo(id);
		
		assertEquals(todoService.getTodo(id),todo);
	}
	
	@Test
	public void newTodoTest()
	{
		Todo todo = new Todo("Test Create","Test 4");
		
		when(todoRepository.save(todo)).thenReturn(todo);
		
		assertEquals(todoService.newTodo(todo),todo);
	}

	@Test
	public void deleteTodoTest()
	{
		Todo todo = new Todo("Test Create","Test 5");
		todoService.newTodo(todo);
		todoService.deleteTodo(todo.getId());
		verify(todoRepository,times(1)).deleteById(todo.getId());
	}
	

}
