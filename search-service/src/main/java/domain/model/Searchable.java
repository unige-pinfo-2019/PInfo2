package domain.model;

public interface Searchable {
	
	public abstract Long getId();
	
	public abstract String getType();
	
	public abstract String getIndex();
	
	public abstract String toJson();
	
	public default String getClassName() {
		return this.getClass().getSimpleName();
	}

}
