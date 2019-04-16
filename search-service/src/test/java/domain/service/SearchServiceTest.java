package domain.service;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.elasticsearch.test.ESTestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Ad;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

	@InjectMocks
	private SearchServiceImpl adServiceImpl;
	
	@Test
	public void testCreateItem() throws IOException {
		Ad ad = getRandomAd();
		
		adServiceImpl.createItem(ad);	
	}
	
	private Ad getRandomAd() {
		Ad ad = new Ad();
		ad.setTitle(ESTestCase.randomAlphaOfLength(ESTestCase.randomInt()));
		ad.setDescription(ESTestCase.randomAlphaOfLength(ESTestCase.randomInt()));
		
		Random r = new Random();
		Long randomDate = -946771200000L + (Math.abs(r.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
		ad.setDate(new Date(randomDate));

		return ad;
	}
}
