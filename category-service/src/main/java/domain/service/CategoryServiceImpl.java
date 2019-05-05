package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import domain.model.Category;

@ApplicationScoped
@Transactional
@Default
public class CategoryServiceImpl implements CategoryService {
	
	@PersistenceContext(unitName = "InmemoryPU")
	private EntityManager em;
	
	@Override
	public Long create(Category category) {
		if (em.contains(category)) {
			throw new IllegalArgumentException("Category already exists");
		} 
		em.persist(category);
		// Sync the transaction to get the newly generated id
		em.flush();
		
		return category.getId();
	}
	
	@Override
	public void delete(Category category) {
		em.remove(em.contains(category) ? category : em.merge(category));
	}
	
	@Override
	public List<Category> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
		criteria.from(Category.class);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Category> getSubCategories(Category category) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Category> q = cb.createQuery(Category.class);
		Root<Category> c = q.from(Category.class);
		return em.createQuery(q.select(c).where(cb.equal(c.get("parentId"), category.getId()))).getResultList();
	}
	
	@Override
	public Category get(Long id) {
		return em.find(Category.class, id);
	}

}
