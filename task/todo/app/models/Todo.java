package models;

import java.util.List;

import javax.persistence.Entity;



import play.data.validation.*;
import play.db.jpa.Model;

@Entity
public class Todo  extends Model {

	@Required
	public String description;
	
	@Required
	@Min(1)
	@Max(3)
	public int priority;

	public Todo(String description, int priority) {
		this.description = description;
		this.priority = priority;
	}

	public static List<Todo> findAllSorted() {
		// TODO Auto-generated method stub
		return Todo.find("order by priority desc, description ASC").fetch();
	}


}
