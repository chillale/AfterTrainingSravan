package rmgYantra;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithoutBdd {
	@Test
	public void createRequest() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("createdBy", "LaxmiReddy");
		jsonObject.put("projectName", "Paya");
		jsonObject.put("Status", "ongoing");
		jsonObject.put("TeamSize", 4);
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body(jsonObject);
		requestSpecification.when();
		Response response = requestSpecification.post("http://localhost:8084/addProject");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		
		
	}

}
