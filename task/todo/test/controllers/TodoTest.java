package controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Todo;

import org.junit.Before;
import org.junit.Test;

import play.mvc.Http;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;


public class TodoTest extends FunctionalTest {
	
		@Before
		public void setUp() {
			Fixtures.deleteDatabase();
		}

		@Test
	    public void shouldReturnAddTodoPage() {
	        Response response = GET("/todo/add");
	        assertIsOk(response);
	    }
		
		
		@Test
	    public void shouldStoreTodos() {
			Map<String, String> params = new HashMap<String, String>();
			
			params.put("description", "En beskrivelse");
			params.put("priority", "1"); 
			
			POST("/todo/create", params);
	        	        
			assertEquals(1, Todo.findAll().size());	        	        
	    }
		
		@Test
	    public void shouldRedirectAfterStoringTodo() {
			Map<String, String> params = new HashMap<String, String>();
			
			params.put("description", "En beskrivelse");
			params.put("priority", "1"); 

	        Response response = POST("/todo/create", params);
	        	        
	        assertHeaderEquals("Location", "/todo/index", response);	 
			assertEquals(1, Todo.findAll().size());	        	        
	    }
		
		@Test
	    public void shouldRequireDescription() {
			Map<String, String> params = new HashMap<String, String>();

			params.put("priority", "1"); 

	        Response response = POST("/todo/create", params);	        
	        assertHeaderEquals("Location", "/todo/add", response);	        	        
	    }
		
		@Test
	    public void shouldRequirePriority() {
			Map<String, String> params = new HashMap<String, String>();
			params.put("description", "En beskrivelse");

	        Response response = POST("/todo/create", params);	        	       
	        assertHeaderEquals("Location", "/todo/add", response);	        	        
	    }
		
		@Test
	    public void shouldNotAllowPriorityLargerThan3() {
			Map<String, String> params = new HashMap<String, String>();
			params.put("description", "En beskrivelse");
			params.put("priority", "4"); 

	        Response response = POST("/todo/create", params);	        	       
	        assertHeaderEquals("Location", "/todo/add", response);        	        
	    }

}
