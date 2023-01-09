package usingDatabase;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import genericUtility.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static genericUtility.JavaFaker.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import static genericUtility.EndPoints.*;
public class UsingDatabaseTest {
	@Test
	public void usingDB() throws SQLException {
		baseURI = "http://localhost";
		port = 8084;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("createdBy", createdBy());
		jsonObject.put("projectName", projectName());
		jsonObject.put("status", status());
		jsonObject.put("teamSize", 7);
		
		HashMap hashMap = new HashMap();
		hashMap.put("createdBy", createdBy());
		hashMap.put("projectName", projectName());
		hashMap.put("status", status());
		hashMap.put("teamSize", 8);
		Response response = given()
		.contentType(ContentType.JSON)
		.body(jsonObject)
		.when()
		.post(addProject);
		
		 String projectID = response.jsonPath().get("projectId");
		 
		 given()
		 .contentType(ContentType.JSON)
		 .pathParam("projectId", projectID)
		 .body(hashMap)
		 .when()
		 .put(updateSingleProject)
		 .then().log().all();
		 
//		 given()
//		 .contentType(ContentType.JSON)
//		 .pathParam("projectId", projectID)
//		 .when()
//		 .delete(deleteSingleProject)
//		 .then().log().all();
		 
		 Driver driverRef = new Driver();
		 DriverManager.registerDriver(driverRef);
		 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root", "root");
		 Statement statement = connection.createStatement();
		 ResultSet resultSet = statement.executeQuery("select project_id from project;");
		 while(resultSet.next()) {
			 if(projectID.equals(resultSet.getString(1))) {
				 System.out.println(resultSet.getString(1));
				 break;
			 }
		 }
		 connection.close();
		 
		
		
	}

}
