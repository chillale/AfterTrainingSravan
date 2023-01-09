package genericUtility;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseApi {
	public DataBaseUtility dataBaseUtility = new DataBaseUtility();
	@BeforeSuite
	public void connectToDB() throws SQLException {
		dataBaseUtility.connectToDataBase();
	}
	@AfterSuite
	public void disconnectToDB() throws SQLException {
		dataBaseUtility.disconnectToDataBase();
	}

}
