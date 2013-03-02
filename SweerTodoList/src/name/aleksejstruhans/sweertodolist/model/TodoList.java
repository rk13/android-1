package name.aleksejstruhans.sweertodolist.model;

import java.nio.ReadOnlyBufferException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TodoList implements Iterable<Todo>{

	private List<Todo> todoList = new LinkedList<Todo>();

	public void add(String text, Category category) {
		Category customCategory = category.isPredefined() ? null : category; 
		Todo todo = new Todo(customCategory, text);
		if (category == Category.TODAY) { 
			todo.setToday(true);
		}
		if (todoList.contains(todo)) { 
			throw new TodoModelError(TodoModelError.Code.TODO_ALREADY_EXISTS, " todo = '" + text + "'");
		}
		todoList.add(todo);
	}

	public Iterable<Todo> getListByCategory(Category category) {
		if (category == Category.ALL) { 
			return Collections.unmodifiableList(todoList);
		}
		boolean todayOnlyRequested = category.equals(Category.TODAY); 
		List<Todo> list = new LinkedList<Todo>();
		for (Todo todo : todoList) { 
			if (todayOnlyRequested) { 
				if (todo.isToday()) { 
					list.add(todo);
				}
			} else if (todo.getCategory().equals(category)) { 
				list.add(todo);
			}
		}
		return Collections.unmodifiableList(list); 
	}

	@Override
	public Iterator<Todo> iterator() {
		return todoList.iterator();
	}
}
