package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import domain.model.Ad;

@ApplicationScoped
@Transactional
@Default
public class AdServiceImpl implements AdService {
	
	@PersistenceContext(unitName = "InmemoryPU")
	private EntityManager em;

	@Override
	public void create(Ad ad) {
		if (em.contains(ad)) {
			throw new IllegalArgumentException("Ad already exists");
		}
		em.persist(ad);
	}

	@Override
	public void delete(Ad ad) {
		em.remove(em.contains(ad) ? ad : em.merge(ad));
	}
	
	@Override 
	public void update(Ad ad) {
		Ad i = em.find(Ad.class, ad.getId());
		if (i == null) {
			throw new IllegalArgumentException("Ad does not exist : " + ad.getId());
		}
		em.merge(ad);
	}

	@Override
	public List<Ad> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ad> criteria = builder.createQuery(Ad.class);
		criteria.from(Ad.class);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public Ad get(Long adId) {
		return em.find(Ad.class, adId);
	}


}
