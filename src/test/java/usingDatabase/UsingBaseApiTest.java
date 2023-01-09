package usingDatabase;
import static genericUtility.EndPoints.*;
import org.testng.annotations.Test;
import static genericUtility.JavaFaker.*;

import genericUtility.BaseApi;
import genericUtility.PojoClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.ResultSet;
import java.sql.SQLException;
public class UsingBaseApiTest extends BaseApi{
	@Test
	public void usingBaseApi() throws SQLException {
		baseURI = "http://localhost";
		port = 8084;
		PojoClass pojoClass = new PojoClass(createdBy(), projectName(), status(), 8);
		Response response = given()
		.contentType(ContentType.JSON)
		.body(pojoClass)
		.when()
		.post(addProject);
		
		String proJectId=response.jsonPath().get("projectId");
		String proName=response.jsonPath().get("projectName");
		
		given()
		.pathParam("projectId", proJectId)
		.when()
		.delete(deleteSingleProject)
		.then().log().all();
		
		ResultSet result = dataBaseUtility.executeQUery("select project_id from project;");
		
		while(result.next()) {
			if(!proJectId.equals(result.getString(1))) {
				System.out.println("validation Successful");
				break;
			}
		}
		
		
	}

}
