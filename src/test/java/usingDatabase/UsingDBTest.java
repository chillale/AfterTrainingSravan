package usingDatabase;
import org.testng.annotations.Test;
import com.mysql.cj.jdbc.Driver;
import static genericUtility.JavaFaker.*;
import static io.restassured.RestAssured.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import genericUtility.PojoClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static genericUtility.EndPoints.*;
public class UsingDBTest {
	@Test
	public void verifyDb() throws SQLException {
		baseURI = "http://localhost";
		port = 8084;
		PojoClass pojoClass = new PojoClass(createdBy(), projectName(), status(), 7);
		PojoClass pojoClass2 = new PojoClass(createdBy(), projectName(), status(), 4);
		Response response = given()
		.contentType(ContentType.JSON)
		.body(pojoClass)
		.when()
		.post(addProject);
		
		String proId=response.jsonPath().get("projectId");
		
		given()
		.pathParam("projectId", proId)
		.contentType(ContentType.JSON)
		.body(pojoClass2)
		.when()
		.put(updateSingleProject)
		.then().log().all();
		
		given()
		.pathParam("projectId", proId)
		.when()
		.delete(deleteSingleProject);
		
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root", "root");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select project_id from project;");
		while(resultSet.next()) {
			if(!proId.equals(resultSet.getString(1))){
				System.out.println("validation Successful");
				break;
			}
		}
		connection.close();
		
	}

}
