package com.person.googlenotesclone.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.person.googlenotesclone.todo.Todo;
import com.person.googlenotesclone.todo.TodoRepository;
import com.person.googlenotesclone.todo.TodoService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GooglenotescloneServiceTest {

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
		
		  Todo retodo = new Todo(3L,"Single Test","test3"); 
		  Optional<Todo> todo = Optional.ofNullable(retodo);
		  when(todoRepository.findById(anyLong())).thenReturn(todo);
		 
		 assertEquals(todoService.getTodo(anyLong()),retodo);
		 
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

		  Todo retodo = new Todo(3L,"Single Test","test3"); 
		  Optional<Todo> todo = Optional.ofNullable(retodo);
		  when(todoRepository.findById(anyLong())).thenReturn(todo);
		  assertEquals(todoService.deleteTodo(anyLong()),retodo);
	}
	
}
