package domain.service;

import java.io.IOException;
import java.util.List;

import domain.model.Searchable;

public interface SearchService {
	
	public void createItem(Searchable item) throws IOException;
	
	public void deleteItem(Searchable item) throws IOException;
	
	public void updateItem(Searchable item) throws IOException;
	
	public List<String> match(String attribute, Object value) throws IOException;
	
}
