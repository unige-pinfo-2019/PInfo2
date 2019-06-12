package domain.service;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import domain.model.Ad;
import lombok.extern.java.Log;

@ApplicationScoped
@Transactional
@Default
@Log
public class AdServiceImpl implements AdService {
	
	@PersistenceContext(unitName = "AdPU")
	private EntityManager em;

	@Override
	public Long create(Ad ad) {
		// Set today date
		ad.setDate(Calendar.getInstance().getTime());
		
		if (em.contains(ad)) {
			throw new IllegalArgumentException("Ad already exists");
		}
		em.persist(ad);
		// Sync the transaction to get the newly generated id
		em.flush();
		
		return ad.getId();
	}

	@Override
	public void delete(Ad ad) {
		em.remove(em.contains(ad) ? ad : em.merge(ad));
	}
	
	@Override 
	public void update(Ad ad) {
		Ad i = get(ad.getId());
		if (i == null) {
			throw new IllegalArgumentException("Ad does not exist : " + ad.getId().toString());
		} else {
			em.merge(ad);
		}
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
		log.info("Ad with id: '" + adId + "' selected");
		return em.find(Ad.class, adId);
	}


}
