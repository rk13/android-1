package name.aleksejstruhans.sweertodolist.model;

public class TodoModelError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	static public enum Code { 
		CATEGORY_ALREADY_EXISTS, TODO_ALREADY_EXISTS;
	} 
	
	private Code code;

	public TodoModelError(Code code, String detailMessage) {
		super(detailMessage);
		this.code = code;
	}

	public Code getCode() {
		return code;
	} 
	
}
