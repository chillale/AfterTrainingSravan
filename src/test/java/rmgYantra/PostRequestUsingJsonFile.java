package rmgYantra;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestUsingJsonFile {
	@Test
	public void createRequestUsingJsonFile() {
		File file = new File("./Complex.json");
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body(file);
        Response response = requestSpecification.post("http://localhost:8084/addProject");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		
	}

}
