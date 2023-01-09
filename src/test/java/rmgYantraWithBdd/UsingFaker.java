package rmgYantraWithBdd;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static genericUtility.JavaFaker.*;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;


public class UsingFaker {
	@Test
	public void usingFaker() {
		JSONObject jsonObject =  new JSONObject();
		jsonObject.put("createdBy",createdBy());
		jsonObject.put("projectName",projectName());
		jsonObject.put("status",status());
		jsonObject.put("teamSize", 4);
		
		given()
		.contentType(ContentType.JSON)
		.body(jsonObject)
		.when()
		.post("http://localhost:8084/addProject")
		.then()
		.log().all().assertThat().statusCode(201);
	}

	
	
	

	
}
