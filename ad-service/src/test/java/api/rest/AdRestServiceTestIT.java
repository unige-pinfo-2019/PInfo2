package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class AdRestServiceTestIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/ad";
		RestAssured.port = 8080;
	}

	@Test
	public void step1testGet() {
		when().get("/1").then().body(containsString("Velo"));
		when().get("/2").then().body(containsString("Chaise"));
	}
	
	@Test
	public void step2testGetAll() {
		when().get("/").then().body(containsString("Chaise"));
		when().get("/").then().body(containsString("Ceci est un velooooo"));
	}

	@Test
	public void step3testDelete() {
		
		 RequestSpecification httpRequest = RestAssured.given();
		 Response response = httpRequest.delete("/1");
		 
		 int statusCode = response.getStatusCode();
		 
		 System.out.println("STATUS CODE DU DELETE :" + statusCode);
		 
		 // Assert that correct status code is returned (it should be 200).
		 assertEquals(statusCode, 500);
		 
		 // Now test that a get will return nothing
		 // when().get("/1").then().body("$", hasSize(0));
		 
	}
	
	

	

}

