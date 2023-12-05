package Practice;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.when;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;
import pojoclassPackage.pojoclass;

public class Practice {
@Test
public void pathparam() {
	baseURI="http://rmgtestingserver";
	port=8084;
	when().get("/projects")
	.then().log().all();
}
@Test
public void pathparam1() {
	baseURI="http://rmgtestingserver";
	port=8084;
	given().pathParam("pid","TY_PROJ_2125")
	
	.when().get("/projects/{pid}")
	.then().log().all();
}

@Test
public void queryparam() {
	baseURI="https://reqres.in";
	given().queryParam("page",3)
	.when().get("/api/users")
	.then().log().all();
}
@Test
public void formparam() {
	int ran=new Random().nextInt(200);
	baseURI="http://rmgtestingserver";
	port=8084;
	/**
	 * {
  "createdBy": "string",
  "projectName": "string",
  "status": "string",
  "teamSize": 0
}
	 */
	given()
	.formParam("createdBy", "Heshika"+ran)
	.formParam("projectName","Proj_009"+ran)
	.formParam("status", "Completed")
	.formParam("teamSize","12")
	.contentType(ContentType.JSON)
	
	.when().post("/addProject")
	.then()
	.assertThat()
	.statusCode(201)
	.contentType(ContentType.JSON)
	.log().all();
}

@Test
public void staticresponseBodyValidation() {
	
	String expdata="TY_PROJ_1335871";
	baseURI="http://rmgtestingserver";
	port=8084;
	
	Response res = when().get("/projects");
	
	String actdata = res.jsonPath().get("[1].projectId");
	Assert.assertEquals(actdata,expdata);
	System.out.println("Data is Verified");
	
	res.then()
	.assertThat()
	.time(Matchers.lessThan(5000l),TimeUnit.MILLISECONDS)
	.log().all();

}
@Test
public void dynamic() {
	String expdata="TY_PROJ_1255454";
	baseURI="http://rmgtestingserver";
	port=8084;
	Response res = when().get("/projects");
	
	boolean flag=false;
	List<String> pid= res.jsonPath().get("projectId");
	for(String PId:pid) 
	{
		if(PId.equalsIgnoreCase(expdata))
		{
			flag=true;
		}
	}
	Assert.assertTrue(flag);
	
	
	res.then()
	.time(Matchers.lessThan(5000l),TimeUnit.MILLISECONDS)
	.log().all();
	System.out.println("Data is Verified");
}

@Test
public void requestchaining() {
	int ran=new Random().nextInt(200);
	baseURI="http://rmgtestingserver";
	port=8084;
	
	pojoclass pojo=new pojoclass("Heshika"+ran, "PROJ_009"+ran, "Completed", 12);
//	JSONObject jobj = new JSONObject();
//	jobj.put("createdBy","Heshika"+ran);
//	jobj.put("projectName","Proj_No001"+ran);
//	jobj.put("status","ongoing");
//	jobj.put("teamSize",10);
	Response res = given()
	.body(pojo)
	.contentType(ContentType.JSON)
	
	 .when().post("/addProject");
	
	String pid = res.jsonPath().get("projectId");
	System.out.println(pid);
	
	res.then().assertThat()
	.statusCode(201)
	.contentType(ContentType.JSON)
	.log().all();
	
	given()
	.pathParam("Pid", pid)
	.when().get("/projects/{Pid}")
	.then().log().all();
	
}
@Test
public void reqreschaining() {
	
	baseURI="https://reqres.in";
	
	Response res = when().get("/api/users?page=2");
	
	//int id = res.jsonPath().get("data[1].id");
	String id = res.jsonPath().getString("data[1].id");
	System.out.println(id);
	
	res.then()
	.assertThat()
	.statusCode(200)
	.contentType("application/json")
	.time(Matchers.lessThan(3000l),TimeUnit.MILLISECONDS)
	.log().all();
	
	given().pathParam("Pid", id)
	.when().get("/api/users/{Pid}")
	.then().log().all();
}

@Test
public void post() throws Throwable {
	baseURI="https://reqres.in";
	File f=new File("D:\\wp3\\com.restassures\\src\\test\\resources\\prac_51.json");
	given().body(f).contentType(ContentType.JSON)
	.when().post("/api/users");

}
@Test
public void staticres() {
	baseURI="https://reqres.in";
	String ExpData="7";
	Response res = when().get("/api/users?page=2");
	String actdata = res.jsonPath().get("id");
	Assert.assertEquals(actdata,ExpData);
	System.out.println("Data is Verified");
	
	res.then().log().all();
}

}
