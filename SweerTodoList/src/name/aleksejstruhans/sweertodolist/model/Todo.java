package name.aleksejstruhans.sweertodolist.model;

public class Todo {
	private Category category; 
	private String text;
	private boolean isToday;
	
	protected Todo(Category category, String text, boolean isToday) {
		super();
		this.category = category;
		this.text = text;
		this.isToday = isToday;
	}

	public Todo(Category category, String text) {
		this.category = category;
		this.text = text;
		this.isToday = false;
	}

	public boolean isToday() {
		return isToday;
	}

	public void setToday(boolean isToday) {
		this.isToday = isToday;
	}

	public Category getCategory() {
		return category;
	}

	public String getText() {
		return text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	} 
	
	
	
}
