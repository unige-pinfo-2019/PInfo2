package domain.service;

import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.smitajit.elasticsearch.rest.mock.ESRestMockCore;
import com.github.smitajit.elasticsearch.rest.mock.runner.ESRestMockRunner;

import domain.model.Ad;

@ExtendWith(MockitoExtension.class)
@RunWith(ESRestMockRunner.class)
public class SearchServiceTest {

	@InjectMocks
	private SearchServiceImpl searchServiceImpl = new SearchServiceImpl("localhost", 9200);
	
	@Test
	public void testCreateItem() {
		
		Ad ad = getRandomAd();
		
		ESRestMockCore.newBuilder()
					  .forMethod("PUT")
					  .forEndPoint("/ads/ad/" + ad.getId())
					  .expectResponse(200, "", ContentType.APPLICATION_JSON)
					  .build();
		
		try {
			searchServiceImpl.createItem(ad);
		} catch(Exception e) {
			fail("Should not have thrown any exception " + e);
		}
	}
	
	@Test
	public void testDeleteItem(){
		
		Ad ad = getRandomAd();
		
		ESRestMockCore.newBuilder()
					  .forMethod("DELETE")
					  .forEndPoint("/ads/ad/" + ad.getId())
					  .expectResponse(200, "", ContentType.APPLICATION_JSON)
					  .build();
		
		ESRestMockCore.newBuilder()
					  .forMethod("PUT")
					  .forEndPoint("/ads/ad/" + ad.getId())
					  .expectResponse(200, "", ContentType.APPLICATION_JSON)
					  .build();
		
		try {
			searchServiceImpl.createItem(ad);
			searchServiceImpl.deleteItem(ad);
		} catch(Exception e) {
			fail("Should not have thrown any exception " + e);
		}
	}
	
	@Test
	public void testUpdateItem(){
		
		Ad ad = getRandomAd();
		
		ESRestMockCore.newBuilder()
					  .forMethod("UPDATE")
					  .forEndPoint("/ads/ad/" + ad.getId())
					  .expectResponse(200, "", ContentType.APPLICATION_JSON)
					  .build();
		
		ESRestMockCore.newBuilder()
					  .forMethod("PUT")
					  .forEndPoint("/ads/ad/" + ad.getId())
					  .expectResponse(200, "", ContentType.APPLICATION_JSON)
					  .build();
		
		try {
			searchServiceImpl.createItem(ad);
			Random r = new Random();
			ad.setPrice(Math.abs(r.nextDouble()));
			searchServiceImpl.updateItem(ad);
		} catch(Exception e) {
			fail("Should not have thrown any exception " + e);
		}
	}
	
	@Test
	public void testMatchItem(){
		
		Ad ad = getRandomAd();
		
		ESRestMockCore.newBuilder()
					  .forMethod("GET")
					  .forEndPoint("_search/" + ad.getId())
					  .expectResponse(200, "", ContentType.APPLICATION_JSON)
					  .build();
		
		ESRestMockCore.newBuilder()
					  .forMethod("PUT")
					  .forEndPoint("/ads/ad/" + ad.getId())
					  .expectResponse(200, "", ContentType.APPLICATION_JSON)
					  .build();
		
		try {
			searchServiceImpl.createItem(ad);
			searchServiceImpl.matchAd(ad.getTitle(), Optional.of((long)1), Optional.of("1"));
		} catch(Exception e) {
			fail("Should not have thrown any exception " + e);
		}
	}
	
	private Ad getRandomAd() {
		Ad ad = new Ad();
		ad.setTitle(UUID.randomUUID().toString());
		//ad.setDescription(UUID.randomUUID().toString());
		
		Random r = new Random();
		ad.setPrice(Math.abs(r.nextDouble()));
		ad.setId(Math.abs(r.nextLong()));
		
		String pattern = "dd/MM/yyyy HH:mm";
		DateFormat df = new SimpleDateFormat(pattern);
		ad.setDate(df.format(Calendar.getInstance().getTime()));

		return ad;
	}
}
