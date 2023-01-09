package usingDatabase;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import static genericUtility.JavaFaker.*;

import genericUtility.EndPoints;
import genericUtility.PojoClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PostRequestUsingDbTest {
	@Test
	public void usingDb() throws SQLException {
		baseURI = "http://localhost";
		port = 8084;
		PojoClass pojoClass = new PojoClass(createdBy(), projectName(), status(), 4);
		PojoClass pojoClass2 = new PojoClass(createdBy(), projectName(), status(), 3);
		Response response = given()
		.contentType(ContentType.JSON)
		.body(pojoClass)
		.when()
		.post(EndPoints.addProject);
		
		String proId=response.jsonPath().get("projectId");
		System.out.println(proId);
		
		given()
		.contentType(ContentType.JSON)
		.body(pojoClass2)
		.pathParam("projectId", proId)
		.when()
		.put(EndPoints.updateSingleProject)
		.then().log().all();
		
		given()
		.pathParam("projectId", proId)
        .when()
        .delete(EndPoints.deleteSingleProject)
        .then().log().all();
		
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root", "root");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select project_id from project;");
		while(resultSet.next()) {
			if(!proId.equals(resultSet.getString(1))) {
				System.out.println("validation Successful");
				break;
			}
		}
		connection.close();
		
		
		
		
		
		
	}

}
