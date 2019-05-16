package api.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import domain.model.Ad;
import domain.service.AdServiceImpl;

// For integration testing
//import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;
// import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class AdRestServiceTest {
	
	@InjectMocks
	private AdRestService adRestService;
	
	@Test
	public void getAdTest() {
		Ad ad = adRestService.get((long) 1);
		assertNotNull(ad);
		assertEquals((double) ad.getId(),(double) (long) 1);
	}
	
	
	
		
}

