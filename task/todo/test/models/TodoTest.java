package models;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


import play.data.validation.Validation;
import play.test.Fixtures;
import play.test.UnitTest;

public class TodoTest extends UnitTest {
	
	@Before
	public void setUp() {
		Fixtures.deleteDatabase();
	}

	@Test
	public void testThatWeCanCreateTodo() {
		Todo todo = new Todo("beskrivelse", 1);
		
		assertEquals("beskrivelse", todo.description);		
		assertEquals(1, todo.priority);
	}
	
	@Test
	public void testThatWeCanStoreTodo() {
		Todo todo = new Todo("beskrivelse", 1);
		
		todo.save();
		
		List<Todo> todos = Todo.findAll();		
		Todo firstTodo = todos.get(0);
			
		assertEquals(1, todos.size());
		assertEquals("beskrivelse", firstTodo.description);
		assertEquals(1, firstTodo.priority);
	}
	
	@Test
	public void testThatWeCanStoreTwoTodos() {
		Todo todo1 = new Todo("beskrivelse", 1);		
		Todo todo2 = new Todo("beskrivelse2", 1);

		todo1.save();
		todo2.save();
		
		List<Todo> todos = Todo.findAll();		
			
		assertEquals(2, todos.size());
	}
	
	@Test
	public void shouldBeSortedByPriority() {
		new Todo("beskrivelse", 1).save();
		new Todo("sdf", 1).save();
		new Todo("sdf", 2).save();
		new Todo("zxc", 2).save();
		new Todo("xcbxc", 3).save();
		new Todo("xcbxc2", 3).save();
		new Todo("xcbxc2", 1).save();
		new Todo("xcbxc", 2).save();
		
		List<Todo> todos = Todo.findAllSorted();
		assertEquals(3, todos.get(0).priority);
		assertEquals(3, todos.get(1).priority);
		assertEquals(2, todos.get(2).priority);
	}
	
	@Test
	public void shouldBeSortedByPriorityAndThenAlphabetically() {
		new Todo("O", 3).save();
		new Todo("B", 3).save();
		new Todo("C", 3).save();
		new Todo("B", 3).save();
		new Todo("B", 3).save();
		new Todo("A", 3).save();
		new Todo("A", 3).save();
		new Todo("F", 3).save();
		
		List<Todo> todos = Todo.findAllSorted();
		assertEquals("A", todos.get(0).description);
		assertEquals("A", todos.get(1).description);
		assertEquals("B", todos.get(2).description);
	}
	
	
	
}
