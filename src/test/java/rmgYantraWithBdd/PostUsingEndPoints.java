package rmgYantraWithBdd;

import java.util.HashMap;
import static genericUtility.JavaFaker.*;
import org.testng.annotations.Test;

import genericUtility.EndPoints;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class PostUsingEndPoints {
	@Test
	public void usingEndpoints() {
		baseURI = "http://localhost";
		port = 8084;
//		HashMap hashMap = new HashMap();
//		hashMap.put("createdBy", createdBy());
//		hashMap.put("projectName", projectName());
//		hashMap.put("status", status());
//		hashMap.put("teamSize", 4);
//		
		given()
		.contentType(ContentType.JSON)
		//.body(hashMap)
		.when()
		.get(EndPoints.projectss)
		.then().log().all().assertThat().statusCode(200);
		
	}

}
