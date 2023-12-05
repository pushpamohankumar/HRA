package RestAssueredLibrary;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.utility.BaseClassAPI;
import com.utility.EndPointsLibrary;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoclassPackage.pojoclass;

public class RestTest extends BaseClassAPI {
	@Test
	public void createProject() throws Throwable {
		
		pojoclass pojo = new pojoclass("Heshika", "PROJ_ID001", "Completed", 12);
		Response resp = given().spec(reqS)
		.body(pojo)
		
		.when()
		.post(EndPointsLibrary.createProject);
		
		String expData = ru.getJSOnData(resp, "projectId");
		System.out.println(expData);
		
		String query ="SELECT * FROM PROJECT;";
		String actdata = du.executeQueryAndGetData(query, 1, expData);
		Assert.assertEquals(actdata,expData );
		System.out.println("Test Case Pass");
		resp.then().log().all();
		
		
	}
}
