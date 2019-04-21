package domain.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import domain.model.User;


@ApplicationScoped
@Transactional
@Default
public class UserServiceImpl implements UserService {
	
	@PersistenceContext(unitName = "InmemoryPU")
	private EntityManager em;	

	@Override
	public void create(User user) {
		if (em.contains(user)) {
			throw new IllegalArgumentException("user already exists");
		}
		em.persist(user);
	}
	
	@Override
	public void delete(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}

	@Override
	public void update(User user) {
		User i = em.find(User.class, user.getId());
		if (i == null) {
			throw new IllegalArgumentException("User does not exist : " + user.getId().toString());
		}
		em.merge(user);
	}

	@Override
	public User get(Long userId) {
		return em.find(User.class, userId);
	}

}





















