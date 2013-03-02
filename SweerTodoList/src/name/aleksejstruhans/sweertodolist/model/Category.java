package name.aleksejstruhans.sweertodolist.model;

import android.graphics.Color;

public class Category implements Comparable<Category>{
	private int color;
	private String name; 
	private boolean isPredefined;
	
	public static final Category ALL = new Category(Color.BLACK, "ALL", true);
	public static final Category TODAY = new Category(Color.BLACK, "TODAY", true);

	protected Category(int color, String name) {
		this.color = color;
		this.name = name;
	}

	private Category(int color, String name, boolean isPredefined) {
		this.color = color;
		this.name = name;
		this.isPredefined = isPredefined;
	}

	public int getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Category another) {
		return name.compareTo(another.name);
	}

	public boolean isPredefined() {
		return isPredefined;
	}
	
	
}
