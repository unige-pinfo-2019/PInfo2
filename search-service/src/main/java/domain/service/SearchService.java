package domain.service;

import java.util.List;

import domain.model.Searchable;

public interface SearchService {
	
	public void createItem(Searchable item);
	
	public void deleteItem(Searchable item);
	
	public void updateItem(Searchable item);
	
	public <T extends Searchable> List<T> match(String query, Class<T> type);
	
}
