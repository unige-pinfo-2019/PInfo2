package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Ad;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
public class AdServiceTest {
	
	@Spy
	@PersistenceContext(unitName = "AdPUTest")
	EntityManager em;
	
	@InjectMocks
	private AdServiceImpl adServiceImpl;

	@Test
	public void testGetAll() {
		int size = initDataStore();
		assertEquals(size, adServiceImpl.getAll().size());
	}
	
	@Test
	public void testGet() {
		initDataStore();
		List<Ad> ads = adServiceImpl.getAll();
		if (ads.isEmpty())
			initDataStore();
		Long id = ads.get(0).getId();
		Ad ad = adServiceImpl.get(id);
		
		assertEquals(ads.get(0).getId(), ad.getId());
		assertEquals(ads.get(0).getTitle(), ad.getTitle());
	}
	
	@Test
	public void testCreate() {
		Ad ad = getRandomAd();
		adServiceImpl.create(ad);
		
		Ad i = em.find(Ad.class, ad.getId());
		
		assertTrue(ad.equals(i));
	}
	
	@Test
	public void testDelete() {
		
		Ad ad = getRandomAd();
		adServiceImpl.create(ad);
		adServiceImpl.delete(ad);
		
		Ad i = em.find(Ad.class, ad.getId());
		
		assertTrue(i == null);
	}
	
	@Test
	public void testUpdate() {
		
		Ad ad1 = getRandomAd();
		adServiceImpl.create(ad1);
		Ad ad2 = getRandomAd();
		ad2.setId(ad1.getId());
		
		adServiceImpl.update(ad2);
		
		Ad i = em.find(Ad.class, ad1.getId());
		assertTrue(ad2.equals(i));
	}
	
	private List<Ad> getAds() {

		List<Ad> ads = new ArrayList<>();
		long numberOfAd = Math.round((Math.random() * 10)) + 1;
		for (int i = 0; i < numberOfAd; i++) {
			ads.add(getRandomAd());
		}
		return ads;
	}
	
	private int initDataStore() {
		int size = adServiceImpl.getAll().size();
		List<Ad> ads = getAds();	
		for (Ad ad : ads) {
			adServiceImpl.create(ad);
		}
		return size + ads.size();
	}
	
	private Ad getRandomAd() {
		Ad ad = new Ad();
		ad.setTitle(UUID.randomUUID().toString());
		ad.setDescription(UUID.randomUUID().toString());
		
		Random r = new Random();
		ad.setPrice(r.nextDouble());
		
		Long randomDate = -946771200000L + (Math.abs(r.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
		ad.setDate(new Date(randomDate));
		
		ad.setUserId(UUID.randomUUID().toString());
		ad.setCategoryId(r.nextLong());

		return ad;
	}

}
