package rmgYantraWithBdd;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtility.PojoClass;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class UsingDataProvider {
	@Test(dataProvider = "provideData")
	public void usingDataProvider(String createdBy, String projectName, String status, int teamSize) {
		PojoClass pojoClass = new PojoClass(createdBy, projectName, status, teamSize);
	   given()
	   .contentType(ContentType.JSON)
	   .body(pojoClass)
	   .when()
	   .post("http://localhost:8084/addProject")
	   .then()
	   .log()
	   .all().assertThat().statusCode(201);
		
		
	}
	@DataProvider
	public Object[][] provideData() {
		Object objArray[][] = new Object[3][4];
		objArray[0][0]="vaishali";
		objArray[0][1]="Nodles";
		objArray[0][2]="completed";
		objArray[0][3] = 9;
		
		objArray[1][0] = "Vaishali";
		objArray[1][1]="Gobi";
		objArray[1][2] = "created";
		objArray[1][3] = 9;
		
		objArray[2][0] = "Hanumant";
		objArray[2][1] = "EggRice";
		objArray[2][2] = "ongoing";
		objArray[2][3] = 7;
		return objArray;
		
		
		
	}

}
