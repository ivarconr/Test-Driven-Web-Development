package controllers;

import java.util.List;

import play.mvc.Controller;

public class Todo extends Controller {

	/**
	 * Iteration 5: just render index.
	 */
	public static void index() {
		List<models.Todo> todos = models.Todo.findAllSorted();	
		render(todos);
	}

	/** 
	 * Iteration 3
	 * 
	 * Should render a form for creating new todo.
	 */
	public static void add() {
		render();
	}

	/**
	 * Iteration 4 : save, no validation 
	 * Iteration 6 : redirect to index
	 * Iteration 10: add validation 
	 * 
	 * Action where the user POST required attributes for creating 
	 * a new todo. This method is responsible for creating the todo
	 * and then redirect the user to the index-action. 
	 * 
	 * @param description
	 * @param priority
	 */
	public static void create(String description, int priority) {
		models.Todo todo = new models.Todo(description, priority);
		
		//todo.save();  //(Iteration 4)	

		//Iteration 10
		if (!todo.validateAndSave()) {
			validation.keep();
			add();
		}
		//End 11. 
		
		//Iteration 6. 
		index();
	}

}
