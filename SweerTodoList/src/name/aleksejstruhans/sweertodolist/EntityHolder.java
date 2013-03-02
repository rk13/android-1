package name.aleksejstruhans.sweertodolist;

import name.aleksejstruhans.sweertodolist.model.CategoryList;
import name.aleksejstruhans.sweertodolist.model.TodoList;

public class EntityHolder {
	private static CategoryList categoryList; 
	private static TodoList todoList;
	public static CategoryList getCategoryList() {
		return categoryList;
	}
	public static void setCategoryList(CategoryList categoryList) {
		categoryList = categoryList;
	}
	public static TodoList getTodoList() {
		return todoList;
	}
	public static void setTodoList(TodoList todoList) {
		todoList = todoList;
	}
	
	
}
