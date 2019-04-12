package domain.model;

public abstract class Searchable {
	
	public abstract Long getId();
	
	public abstract String getType();
	
	public abstract String getIndex();
	
	public abstract String toJson();
	
	public String getClassName() {
		return this.getClass().getSimpleName();
	}

}
