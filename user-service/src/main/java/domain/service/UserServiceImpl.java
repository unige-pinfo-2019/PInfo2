package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import domain.model.User;


@ApplicationScoped
@Transactional
@Default
public class UserServiceImpl implements UserService {
	
	@PersistenceContext(unitName = "InmemoryPU")
	private EntityManager em;	

	@Override
	public long create(User user) {
		if (em.contains(user)) {
			throw new IllegalArgumentException("user already exists");
		}
		em.persist(user);
		em.flush();
		
		return user.getId();
	}
	
	@Override
	public void delete(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}

	@Override
	public void update(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User does not exist");
		}
		em.merge(user);
	}

	@Override
	public User get(Long userId) {
		return em.find(User.class, userId);
	}

	@Override
	public List<User> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		criteria.from(User.class);
		return em.createQuery(criteria).getResultList();
	}

}














