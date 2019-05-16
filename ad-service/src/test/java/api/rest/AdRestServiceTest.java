package api.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Ad;
import domain.service.AdServiceImpl;
import eu.drus.jpa.unit.api.JpaUnit;

// For integration testing
//import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;
// import static io.restassured.module.jsv.JsonSchemaValidator.*;


@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
public class AdRestServiceTest {
	
	@InjectMocks
	private AdRestService adRestService;
	
	
	
	
	
	@Test
	public void testGet() {
		initDataStore();
		List<Ad> ads = adRestService.getAll();
		if (ads.isEmpty())
			initDataStore();
		Long id = ads.get(0).getId();
		Ad ad = adRestService.get(id);
		
		assertEquals(ads.get(0).getId(), ad.getId());
		assertEquals(ads.get(0).getTitle(), ad.getTitle());
	}
	
	
	
	///// Methods for testing
	private Ad getRandomAd() {
		Ad ad = new Ad();
		ad.setTitle(UUID.randomUUID().toString());
		ad.setDescription(UUID.randomUUID().toString());
		
		Random r = new Random();
		ad.setPrice(r.nextDouble());
		
		Long randomDate = -946771200000L + (Math.abs(r.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
		ad.setDate(new Date(randomDate));
		
		ad.setUserId(r.nextLong());
		ad.setCategoryId(r.nextLong());

		return ad;
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
		int size = adRestService.getAll().size();
		List<Ad> ads = getAds();	
		for (Ad ad : ads) {
			adRestService.create(ad);
		}
		return size + ads.size();
	}
	/////
	
	
		
}

