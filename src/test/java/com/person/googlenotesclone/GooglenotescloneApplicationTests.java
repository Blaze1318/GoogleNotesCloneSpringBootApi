package com.person.googlenotesclone;

import static org.mockito.Mockito.when;

import java.util.Arrays;

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
@AutoConfigureMockMvc(addFilters = true)
class GooglenotescloneApplicationTests {

	@MockBean
	private TodoService service;
	
	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("Should get all the todos from GET Endpoint - /api/v1/to")
	public void testTodo() throws Exception
	{
		Todo todo = new Todo(1L,"Test1","I'm the first test");
		Todo todo2 = new Todo(2L,"Test2","I'm the second test");
		
		when(service.getTodos()).thenReturn(Arrays.asList(todo,todo2));
		
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/todo"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1L)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Test1")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("I'm the first test")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2L)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("Test2")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.is("I'm the second test")));	
	}
}
