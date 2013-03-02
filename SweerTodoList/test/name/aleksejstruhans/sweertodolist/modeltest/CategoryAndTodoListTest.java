package name.aleksejstruhans.sweertodolist.modeltest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import name.aleksejstruhans.sweertodolist.model.Category;
import name.aleksejstruhans.sweertodolist.model.CategoryList;
import name.aleksejstruhans.sweertodolist.model.Todo;
import name.aleksejstruhans.sweertodolist.model.TodoList;
import name.aleksejstruhans.sweertodolist.model.TodoModelError;

import org.junit.Before;
import org.junit.Test;

import android.graphics.Color;

public class CategoryAndTodoListTest {

	private CategoryList categoryList;
	private TodoList todoList;

	@Before
	public void setUp() {
		todoList = new TodoList();
		categoryList = CategoryList.createInitialList();
	}

	@Test
	public void testCustomCategoryRemovable() {
		categoryList.add("categoryName", Color.BLUE);
		for (Category category : categoryList) {
			if (category.getName().equals("categoryName")) {
				assertTrue(categoryList.isRemovableCategory(category, todoList));
			}
		}
	}

	@Test
	public void testCustomNonEmptyCategoryIsntRemovable() {
		Category category = addAndGetCategory("aCategory");
		todoList.add("todoName", category);
		assertFalse(categoryList.isRemovableCategory(category, todoList));
	}

	@Test
	public void testPredefinedCategoryIsntRemovable() {
		for (Category category : categoryList) {
			assertFalse(categoryList.isRemovableCategory(category, todoList));
		}
	}

	@Test
	public void addCategoryWithExistingNameThrowsError() {
		final String categoryName = "sameCategoryName";
		TodoModelError.Code code = null;
		categoryList.add(categoryName, Color.BLUE);
		try {
			categoryList.add(categoryName, Color.BLUE);
			fail("No exception thrown");
		} catch (TodoModelError e) {
			assertEquals(TodoModelError.Code.CATEGORY_ALREADY_EXISTS,
					e.getCode());
		}
	}

	@Test
	public void addTodoWithSameTextThrowsError() {
		TodoModelError.Code code = null;
		todoList.add("todoText", Category.ALL);
		try {
			todoList.add("todoText", Category.ALL);
			fail("No exception thrown");
		} catch (TodoModelError e) {
			assertEquals(TodoModelError.Code.TODO_ALREADY_EXISTS,
					e.getCode());
		}
	}

	
	@Test
	public void testAllShowsTodosOfCustomCategory() {
		Category category = addAndGetCategory("aCategory");
		todoList.add("todoName", category);
		boolean shown = false;
		for (Todo todo : todoList.getListByCategory(Category.ALL)) {
			if (todo.getText().equals("todoName")) {
				shown = true;
				break;
			}
		}
		assertTrue(shown);
	}

	@Test
	public void testAddAndGetCategoryGivesDifferentCategories() {
		Category categoryA = addAndGetCategory("aCategory");
		Category categoryB = addAndGetCategory("bCategory");
		assertTrue(categoryA != categoryB);
	}

	@Test
	public void testCustomCategoryDoesntShowAnother() {
		Category categoryA = addAndGetCategory("aCategory");
		Category categoryB = addAndGetCategory("bCategory");

		todoList.add("todoNameA", categoryA);
		todoList.add("todoNameB", categoryB);
		boolean categoryAShowsTodoFromB = false;
		for (Todo todo : todoList.getListByCategory(categoryA)) {
			if (todo.getText().equals("todoNameB")) {
				categoryAShowsTodoFromB = true;
				break;
			}
		}
		assertFalse(categoryAShowsTodoFromB);
	}

	private Category addAndGetCategory(String name) {
		categoryList.add(name, Color.BLUE);
		for (Category category : categoryList) {
			if (category.getName().equals(name)) {
				return category;
			}
		}
		throw new RuntimeException("Can't get just added category");
	}

	@Test
	public void testNotTodayTaskNotInTodayTodoList() {
		Category category = addAndGetCategory("aCategory");
		Todo todo = addAndGetTodo("todoName", category);
		for (Todo todoFromList : todoList.getListByCategory(Category.TODAY)) {
			assertFalse(todoFromList.equals(todo));
		}
	}
	
	@Test
	public void testTodayTaskInTodayTodoList() {
		Category category = addAndGetCategory("aCategory");
		Todo todo = addAndGetTodo("todoName", category);
		todo.setToday(true);
		boolean found = false;
		for (Todo todoFromList : todoList.getListByCategory(Category.TODAY)) {
			if (todoFromList.equals(todo)) { 
				found = true; 
				break;
			}
		}
		assertTrue(found);
	}

	private Todo addAndGetTodo(String todoText, Category category) {
		todoList.add(todoText, category);
		for (Todo todo : todoList) {
			if (todo.getText().equals(todoText)) {
				return todo;
			}
		}
		throw new RuntimeException("Can't get just added todo");
	}

	@Test
	public void testAddToTodaySetsTodayAndAddsToAll() {
		Todo todo = addAndGetTodo("todoName", Category.TODAY);
		boolean found = false;
		for (Todo todoFromList : todoList.getListByCategory(Category.ALL)) {
			if (todoFromList.equals(todo) && todo.isToday()) { 
				found = true; 
				break;
			}
		}
		assertTrue(found);
	}

	
}
