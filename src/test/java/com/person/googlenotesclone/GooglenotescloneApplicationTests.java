package com.person.googlenotesclone;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.person.googlenotesclone.todo.Todo;
import com.person.googlenotesclone.todo.TodoController;
import com.person.googlenotesclone.todo.TodoService;



@RunWith(SpringRunner.class)
@WebMvcTest(controllers  = TodoController.class)
class GooglenotescloneApplicationTests {

	@MockBean
	private TodoService service;
	
	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("Should get all the todos from GET Endpoint - /api/v1/todo")
	public void testTodo() throws Exception
	{
		when(service.getTodos()).thenReturn(Stream.of(new Todo(1L,"Hey there","test"),
				new Todo(2L,"Hey there","test2")).collect(Collectors.toList()));
		
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/todo")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1L)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Hey there")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("test")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2L)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("Hey there")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.is("test2")));	
	}
	
	@Test
	@DisplayName("Should get all the todos from POST Endpoint - /api/v1/todo")
	public void testNewTodo() throws Exception
	{
		Todo todo = new Todo(1L,"Test1","I'm the first test");
		
		when(service.newTodo(todo)).thenReturn(todo);
		
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/todo")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$id", Matchers.is(1L)))
		.andExpect(MockMvcResultMatchers.jsonPath("$title", Matchers.is("Test1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$description", Matchers.is("I'm the first test")));
	}
	
	@Test
	public void testGetTodo()
	{
		
	}
}
