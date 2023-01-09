package rmgYantraWithBdd;

import java.util.HashMap;

import org.testng.annotations.Test;


import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class UsingHashMap {
	@Test
	public void usingHashMap() {
		HashMap hashMap = new HashMap();
		hashMap.put("createdBy", "Harish");
		hashMap.put("projectName", "Chapathi");
		hashMap.put("status", "completed");
		hashMap.put("teamSize",7);
		
		given()
		.contentType(ContentType.JSON)
		.body(hashMap)
		.when()
		.post("http://localhost:8084/addProject")
		.then()
		.log().all().statusCode(201);
		
	}

}
