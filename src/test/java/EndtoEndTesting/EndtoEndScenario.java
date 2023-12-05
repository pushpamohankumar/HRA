package EndtoEndTesting;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.utility.EndPointsLibrary;
import com.utility.IConstant;
import com.utility.javautility;

import static io.restassured.RestAssured.*;

public class EndtoEndScenario {

	@Test
	public void scenarionEndtoend() throws Throwable {

		Connection con = null;
		WebDriver driver = new ChromeDriver();

		javautility jlib = new javautility();
		String pname = "HeshikaGowda" + jlib.randomNum();

		baseURI = "http://rmgtestingserver";
		port = 8084;

		driver.get("http://rmgtestingserver:8084/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(pname);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("IshuGowda");

		WebElement sel = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		Select s = new Select(sel);
		s.selectByVisibleText("Completed");

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String id = driver.findElement(By.xpath("(//td[text()='" + pname + "']/ancestor::tr/td)[1]")).getText();
		System.out.println(id);

		when().get(EndPointsLibrary.getSingleProject + id).then().log().all();

		// Database Verification
		String query = "SELECT * FROM PROJECT";
		Driver driver1 = new Driver();
		DriverManager.registerDriver(driver1);
		con = DriverManager.getConnection(IConstant.dbURL, IConstant.dbUserName, IConstant.dbPassword);
		Statement state = con.createStatement();
		ResultSet result = state.executeQuery(query);
		boolean flag = false;
		while (result.next()) {
			if (id.equals(result.getString(1))) {
				flag = true;
				break;
			}
		}
		if (flag == true) {
			System.out.println("Data is Present in DB");
		} else {
			System.out.println("Data is not Present in DB");
		}
		driver.quit();
	}

}
