package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Category;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@Spy
	@PersistenceContext(unitName = "CategoryPUTest")
	EntityManager em;
	
	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;

	@Test
	public void testGetAll() {
		int size = initDataStore();
		assertEquals(size, categoryServiceImpl.getAll().size());
	}
	
	@Test
	public void testCreate() {
		Category category = getRandomCategory();
		categoryServiceImpl.create(category);
		
		Category i = em.find(Category.class, category.getId());
		
		assertTrue(category.equals(i));
	}
	
	@Test
	public void testDelete() {
		
		Category category = getRandomCategory();
		categoryServiceImpl.create(category);
		categoryServiceImpl.delete(category);
		
		Category i = em.find(Category.class, category.getId());
		
		assertTrue(i == null);
	}
	
	@Test
	public void testGetSubGategories() {
		
		Category categoryParent = new Category();
		categoryParent.setName("livres");
		Category category1 = new Category();
		Category category2 = new Category();
		Category category3 = new Category();
		category1.setName("histoire");
		category2.setName("informatique");
		category3.setName("chimie");
		
		Long parentId = categoryServiceImpl.create(categoryParent);
		category1.setParentId(parentId);
		category2.setParentId(parentId);
		category3.setParentId(parentId);
		
		List<Category> categories = getCategories();
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);
		
		for (Category category : categories) {
			categoryServiceImpl.create(category);
		}
		
		int size = categoryServiceImpl.getSubCategories(categoryParent).size();
		
		assertEquals(3, size);
	}
	
	@Test
	public void getCategory() {
		Category category = getRandomCategory();
		Long id = categoryServiceImpl.create(category);
		
		Category requestedCateg = categoryServiceImpl.get(id);
		
		assertEquals(category.getName(), requestedCateg.getName());
		assertEquals(category.getParentId(), requestedCateg.getParentId());
	}
	
	
	
	private List<Category> getCategories() {

		List<Category> categories = new ArrayList<>();
		long numberOfCategory = Math.round((Math.random() * 10)) + 1;
		for (int i = 0; i < numberOfCategory; i++) {
			categories.add(getRandomCategory());
		}
		return categories;
	}
	
	private int initDataStore() {
		int size = categoryServiceImpl.getAll().size();
		List<Category> categories = getCategories();	
		for (Category category : categories) {
			categoryServiceImpl.create(category);
		}
		return size + categories.size();
}
	
	private Category getRandomCategory() {
		Category category = new Category();
		category.setName(UUID.randomUUID().toString());

		return category;
}

}
