package genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
	public Connection connection;
    public void connectToDataBase() throws SQLException {
    	Driver driverRef = new Driver();
    	DriverManager.registerDriver(driverRef);
    	connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root", "root");
    }
    
    public void disconnectToDataBase() throws SQLException {
    	connection.close();
    }
    public ResultSet executeQUery(String query) throws SQLException {
    	Statement statement = connection.createStatement();
    	ResultSet resultSet = statement.executeQuery(query);
    	return resultSet;
    }
}