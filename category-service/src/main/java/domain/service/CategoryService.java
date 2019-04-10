package domain.service;

import java.util.List;

import domain.model.Category;

public interface CategoryService {
	
	public void create(Category category);

	public void delete(Category category);

	public List<Category> getAll();

	public List<Category> getSubCategories(Category category);
	
	public Category get(Long id);

}
