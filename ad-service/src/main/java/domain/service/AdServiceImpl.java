package domain.service;

import java.util.ArrayList;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import domain.model.Ad;


@ApplicationScoped
@Transactional
@Default
public class AdServiceImpl implements AdService {

	@PersistenceContext(name="InmemoryPU")
	EntityManager em;

	@Override
	public boolean createAd(Ad ad) {
		em.persist(ad);
		return true;
	}

	@Override
	public ArrayList<Ad> get(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ad> get(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ad> getAll() {
		ArrayList<Ad> Ad = (ArrayList<domain.model.Ad>) em.createQuery("SELECT a FROM Ad a", Ad.class).getResultList();
		return Ad;
	}

}
