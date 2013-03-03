package name.aleksejstruhans.sweertodolist;

import name.aleksejstruhans.sweertodolist.model.Category;

public class CategoryWrapper {

	private final Category category; 
	
	public Category getCategory() {
		return category;
	}

	public CategoryWrapper(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() { 
		return category.getName();
	}

}
