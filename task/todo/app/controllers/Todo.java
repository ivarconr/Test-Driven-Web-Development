package controllers;

import java.util.List;

import play.mvc.Controller;

public class Todo extends Controller {
	
	public static void index() {
		List<models.Todo> todos = models.Todo.findAllSorted();
		
		render(todos);
    }
	 
	 public static void add() {
        render();
    }
	 
	 public static void create(String description, int priority) {
		 models.Todo todo= new models.Todo(description, priority); 
		 
		 if (!todo.validateAndSave()) {
			 validation.keep();
			 add();
		 }
		 index();
	 }

}
