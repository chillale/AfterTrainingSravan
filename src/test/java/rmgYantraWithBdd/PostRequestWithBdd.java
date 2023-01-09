package rmgYantraWithBdd;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class PostRequestWithBdd {
	@Test
	public void createRequest() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("createdBy", "Raja");
		jsonObject.put("projectName", "Chikki");
		jsonObject.put("status", "completed");
		jsonObject.put("teamSize", 3);
		
		given()
		.contentType(ContentType.JSON)
		.body(jsonObject)
		.when()
		.post("http://localhost:8084/addProject")
		.then()
		.log().all().assertThat().statusCode(201);
	}

}
