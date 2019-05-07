package domain.service;

import java.util.List;
import java.util.Optional;

import domain.model.Ad;
import domain.model.Searchable;

public interface SearchService {
	
	public void createItem(Searchable item);
	
	public void deleteItem(Searchable item);
	
	public void updateItem(Searchable item);
	
	public List<Ad> matchAd(String query, Optional<Long> categoryId, Optional<Long> userId);
	
}
