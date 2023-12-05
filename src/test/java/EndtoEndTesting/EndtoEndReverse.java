package EndtoEndTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.utility.IConstant;

public class EndtoEndReverse {
@Test
public void endtoEnd() throws Throwable {
	
	Driver driver=new Driver();
	DriverManager.registerDriver(driver);
	
	Connection  con=DriverManager.getConnection(IConstant.dbURL,IConstant.dbUserName,IConstant.dbPassword);
	Statement state = con.createStatement();
	
	state.executeUpdate(null);
}
}
