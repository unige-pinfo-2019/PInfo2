package api;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.containsString;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

public class CategoryRestServiceTestIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/category";
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
			body(containsString("Vehicule"));
	}
	
	@Test //GET
	public void step2testGetAll() {
		when().
			get("/").
		then().
			assertThat().
			statusCode(200).
			and().
			body(containsString("Velo")).
			and().
			body(containsString("Vehicule"));
	}
	
	@Test //GET
	public void step3testGetChildren() {
		when().
			get("/1/children").
		then().
			assertThat().
			statusCode(200).
			and().
			body(containsString("Velo")).
			and().
			body(containsString("Voiture"));
	}
	
	@Test //POST
	public void step4testCreate() {
		JSONObject jsonObj = new JSONObject()
		                             .put("id",4)
		                             .put("name","Train")
		                             .put("parentId",1);
		     
		given().
			log().all().
			contentType("application/json\r\n").
        	body(jsonObj.toString()).
        when().
        	post("/").
        then().
        	log().all().
        	assertThat().
        	statusCode(200);
	}
	
	@Test //DELETE
	public void step5testDelete() {
		when().
			delete("/1").
		then().
			assertThat().
			statusCode(200);
	}
	@Test //GET after DELETE (Exception)
	public void step6testGetafterDelete() {
		when().
			get("/10").
		then().
			log().body().
			assertThat().
	    	statusCode(204); //No content
	}
	

}

