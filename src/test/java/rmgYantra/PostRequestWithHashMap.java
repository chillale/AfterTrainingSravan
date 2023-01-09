package rmgYantra;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithHashMap {
	@Test
	public void createRequest() {
		HashMap hashMap = new HashMap();
		hashMap.put("createdBy", "Amir");
		hashMap.put("projectName", "Biriyan");
		hashMap.put("status", "completed");
		hashMap.put("teamSize", 3);
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body(hashMap);
		requestSpecification.when();
		Response response = requestSpecification.post("http://localhost:8084/addProject");
		//response.prettyPrint();
		System.out.println(response.getBody());
		
		
	}

}
