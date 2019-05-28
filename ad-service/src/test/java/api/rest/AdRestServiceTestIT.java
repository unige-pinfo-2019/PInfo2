package api.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class AdRestServiceTestIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/ad";
		RestAssured.port = 8080;
	}

	@Test
	public void testGet() {
		when().get("/2").then().body(containsString("Chaise"));
	}
	
	@Test
	public void testGetAll() {
		when().get("/").then().body(containsString("Velo"));
	}

	//@Test
	//public void testCount() {
	//	when().get("/count").then().body(containsString("5"));
	//}

	

}

