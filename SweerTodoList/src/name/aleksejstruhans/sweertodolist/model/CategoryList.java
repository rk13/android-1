package name.aleksejstruhans.sweertodolist.model;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CategoryList implements Iterable<Category> {

	public static CategoryList createInitialList() {
		return new CategoryList();
	}

	private SortedSet<Category> categoryList = new TreeSet<Category>();

	public void add(String name, int color) {
		Category category = new Category(color, name);
		if (categoryList.contains(category)) { 
			throw new TodoModelError(TodoModelError.Code.CATEGORY_ALREADY_EXISTS, " category = '" + name + "'");
		}
		categoryList.add(category); 
	}

	@Override
	public Iterator<Category> iterator() {
		return categoryList.iterator();
	}
	
	public boolean isRemovableCategory(Category category, TodoList todolist) {
		return (!category.isPredefined()) && (!todolist.getListByCategory(category).iterator().hasNext());
	}


}
