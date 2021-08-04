package com.person.googlenotesclone;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Hey there"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("test"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Hey there"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("test2"));
	}
	
	@Test
	@DisplayName("Should get all the todos from POST Endpoint - /api/v1/todo")
	public void testNewTodo() throws Exception
	{
		Todo todo = new Todo(1L,"Test1","I'm the first test");
		
		when(service.newTodo(todo)).thenReturn(todo);
		
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/todo")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{}")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@DisplayName("Should get A single todo from GET Endpoint - /api/v1/todo/{some_id}")
	public void testGetTodo() throws Exception
	{
		Todo todo = new Todo(1L,"Test1","I'm the first test");
		
		when(service.getTodo(1L)).thenReturn(todo);
		
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/todo/{1L}",todo.getId())
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
		.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test1"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("I'm the first test"));
	}
	
	@Test
	@DisplayName("Should get A single todo from DELETE Endpoint - /api/v1/todo/{some_id}")
	public void deleteTodoTest() throws Exception
	{
		Todo todo = new Todo(1L,"Test1","I'm the first test");
		
		when(service.deleteTodo(1L)).thenReturn(todo);
		
		mvc.perform(MockMvcRequestBuilders.delete("/api/v1/todo/{1L}",todo.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@DisplayName("Should update a single todo from PUT Endpoint - /api/v1/todo/{some_id}")
	public void updateTodoTest() throws Exception
	{
		
		 String content= "{\n" +
	                "\"id\":1L,\n" +
	                "\"title\":\"iPhoneX\",\n" +
	                "\"descripton\":\"Mobiles\"\n" +
	                "}";
		
		Todo todo = new Todo(1L,"Test1","I'm the first test");

		
		
		mvc.perform(MockMvcRequestBuilders.put("/api/v1/todo/{1L}",todo.getId())
		.contentType(MediaType.APPLICATION_JSON)
		.content(content)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		 .andDo(print());
		
	}
}
