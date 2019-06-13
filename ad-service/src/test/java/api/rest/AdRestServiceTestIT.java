package api.rest;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.containsString;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;


public class AdRestServiceTestIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/ad";
		RestAssured.port = 8080;
		RestAssured.defaultParser = Parser.JSON;
	}

	@Test //GET
	public void step1testGet() {
		when().
			get("/1").
		then().
			assertThat().
	    	statusCode(200).
	    	and().
			body(containsString("Velo"));
		
		when().
			get("/2").
		then().
			assertThat().
			statusCode(200).
			and().
			body(containsString("Chaise"));
	}
	
	@Test //GET
	public void step2testGetAll() {
		when().
			get("/").
		then().
			assertThat().
			statusCode(200).
			and().
			body(containsString("Chaussure")).
			and().
			body(containsString("Ceci est un ordinateur"));
	}

	
	@Test
	public void step5testDelete() {
		when().
			delete("/1").
		then().
			assertThat().
			statusCode(500); //unsupported media type
	}

}

