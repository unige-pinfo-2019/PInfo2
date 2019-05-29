package api.rest;

import domain.model.Ad;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


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
		
		// CHECK THE CONTENT OF A AD VIA GET
		when().
			get("/2").
		then().
			log().body().
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
			body(containsString("Chaise")).
			and().
			body(containsString("Ceci est un velooooo"));
	}
	
	@Test //PUT
	public void step3testUpdate() {
		
		Response response =
	    when().
	    	get("/2").
	    then().
	        contentType(ContentType.JSON).  
	        extract().response(); 
		
		JsonPath jsonResponse = response.jsonPath();
		
		String new_description = "Ceci est une magnifique chaise";
		Map<String, Object>  new_chaise = new HashMap<>();		    				
		
		new_chaise.put("id", 			jsonResponse.get("id"));
		new_chaise.put("title", 		jsonResponse.get("title"));
		new_chaise.put("description", 	new_description);	
		new_chaise.put("price", 		jsonResponse.get("price"));
		new_chaise.put("date", 			jsonResponse.get("date"));
		new_chaise.put("categoryId", 	jsonResponse.get("categoryId"));
		new_chaise.put("userId", 		jsonResponse.get("userId"));
		new_chaise.put("imageIds",  	jsonResponse.get("imageIds"));

		//System.out.println("NEW_CHAISE ==== : " + Arrays.toString(new_chaise.entrySet().toArray()));
		
		response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(new_chaise)
				.when()
				.put("/").  
				then().
		        extract().response(); 
		
		System.out.println("PUT Response\n" + response.jsonPath().toString());
		// tests
		response.then().body("description", is(new_description));

//		given().
//			contentType(ContentType.JSON).
//		    body(new_chaise).
//		when().
//			put("/").
//		then();
//			//assertThat().
//		    //statusCode(200);
		
		System.out.println("VERIFICATIOOOOOOOn");
		when().
			get("/2").
		then().
			log().body().
			assertThat().
			statusCode(200).
			and().
			body(containsString("Chaise"));
		System.out.println("VERIFICATIOOOOOOOn");
		
	}
	
	@Test //POST
	public void step4testCreate() {
			
		Map<String, Object>  new_ad = new HashMap<>();
		//new_ad.put("id", 7);
		new_ad.put("title", "testCreate");
		new_ad.put("description", "Test de creation d'ad");
		new_ad.put("price", 99.9);
		new_ad.put("categoryId", 2);
		new_ad.put("userId", 1);

		given().
			contentType(ContentType.JSON).
		    body(new_ad).
		when().
			post("/").
		then().
			assertThat().
		    statusCode(201);
	}

	@Test //DELETE
	public void step90testDelete() {
		Response response = 
		when().
			delete("/1").
		then().
			assertThat().
			statusCode(200).
		extract().
        	response(); 
		
		// Ils sont la les COrs filter
		String header = response.header("Access-Control-Allow-Methods");
		System.out.println("HEADERRRRRR :" + header);
	}
	
	

	

}

