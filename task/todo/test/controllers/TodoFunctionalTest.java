package controllers;
import java.util.HashMap;
import java.util.Map;

import models.Todo;

import org.junit.Before;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;


public class TodoFunctionalTest extends FunctionalTest  {
	
	/**
	 * Required to allow our functional test to run in isolation. 
	 */
	@Before
	public void setUp() {
		Fixtures.deleteDatabase();
	}

	/**
	 * Iteration 3. 
	 * 
	 * The task here is to require us to implement an "add" controller
	 * which has an add-action, where the form- view for creating new 
	 * todo's will be placed. 
	 * 
	 */
	@Test
    public void shouldReturnOkForAddTodoPage() {
        Response response = GET("/todo/add");
        assertIsOk(response);
    }
	
	/**
	 * Iteration 4. 
	 * 
	 * The task is to create an "create" action on the TodoController 
	 * which takes in two params, description and priority, and creates
	 * & saves a new todo based on those parameters. 
	 * 
	 */
	@Test
    public void shouldCreateAndStoreTodo() {
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("description", "En beskrivelse");
		params.put("priority", "1"); 
		
		POST("/todo/create", params);
        	        
		assertEquals(1, Todo.findAll().size());	        	        
    }
	
	/**
	 * Iteration 5.
	 * 
	 * Should be possible to  GET "/todo/index"
	 */
	@Test
    public void shoulGetTodoIndex() {
        Response response = GET("/todo/index");	        	       
        assertIsOk(response);      	        
    }
	
	
	
	/**
	 * Iteration 6.
	 * 
	 * We should be redirected to "todo/index" after POSTING an TODO. 
	 */
	@Test
    public void shouldRedirectAfterStoringTodo() {
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("description", "En beskrivelse");
		params.put("priority", "1"); 

        Response response = POST("/todo/create", params);
        
        assertHeaderEquals("Location", "/todo/index", response);	
    }
	
	
	/**
	 * Iteration 10
	 */
	@Test
    public void shouldRequireDescription() {
		Map<String, String> params = new HashMap<String, String>();

		params.put("priority", "1"); 

        Response response = POST("/todo/create", params);	        
        assertHeaderEquals("Location", "/todo/add", response);	        	        
    }
	
	/**
	 * Bonus task x
	 */
	@Test
    public void shouldRequirePriority() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("description", "En beskrivelse");

        Response response = POST("/todo/create", params);	        	       
        assertHeaderEquals("Location", "/todo/add", response);	        	        
    }
	
	/**
	 * Bonus task y
	 */
	@Test
    public void shouldNotAllowPriorityLargerThan3() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("description", "En beskrivelse");
		params.put("priority", "4"); 

        Response response = POST("/todo/create", params);	        	       
        assertHeaderEquals("Location", "/todo/add", response);        	        
    }
}
