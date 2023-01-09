package usingDatabase;
import static genericUtility.EndPoints.*;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import static genericUtility.JavaFaker.*;

import genericUtility.BaseApi;
import genericUtility.PojoClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.ResultSet;
import java.sql.SQLException;
public class UsingBaseTest extends BaseApi{
	@Test
	public void createRequest() throws SQLException {
		baseURI = "http://localhost";
		port = 8084;
		PojoClass pojoClass = new PojoClass(createdBy(), projectName(), status(), 8);
		Response response = given()
		.contentType(ContentType.JSON)
		.body(pojoClass)
		.when()
		.post(addProject);
		
		String proId=response.jsonPath().get("projectId");
		given()
		.pathParam("projectId", proId)
		.when()
		.delete(deleteSingleProject);
		
		ResultSet result = dataBaseUtility.executeQUery("select project_id from project;");
		while(result.next()) {
			if(!proId.equals(result.getString(1))) {
				System.out.println("validation successful");
				break;
			}
		}
		
	}

}
