package parametersinrestassured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.when;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;
import pojoclassPackage.pojoclass;
import pojoclassPackage.pojoclass1;

public class parameters {
@Test
public void pathparam() {
	baseURI="http://rmgtestingserver";
	port=8084;
	
	given()
	.pathParam("id", "TY_PROJ_1704")
	
	.when()
	.get("/projects/{id}")
	
	.then()
	.log().all();
}

@Test
public void queryparam() {
	baseURI="https://reqres.in/";
	
	
	given()
	.queryParam("page", 3)
	
	.when()
	.get("/api/users")
	
	.then()
	.log().all();
	
}

@Test
public void formparam() {
	baseURI="http://rmgtestingserver";
	port=8084;
	
	given()
	.formParam("createdBy", "HeshikaGowda02")
	.formParam("projectName", "PROJ_N09")
	.formParam("status", "on going")
	.formParam("teamSize", 10)
	
	
	.when()
	.post("/addProject")
	
	
	.then()
	.log().all();
	
}

@Test
public void staticResBodyValidation() {
	
	String expdata="TY_PROJ_1341";
	baseURI="http://rmgtestingserver";
	port=8084;
	
	Response res = when()
	.get("/projects");//terminate
	
	String actdata=res.jsonPath().get("[2].projectId");
	Assert.assertEquals(actdata, expdata);
	System.out.println("Data is Verified");
	
	 res.then()
	.assertThat()
	.time(Matchers.lessThan(5000l),TimeUnit.MILLISECONDS);
	
}
@Test
public void dynamic() {
	String expdata="TY_PROJ_1341";
	baseURI="http://rmgtestingserver";
	port=8084;
	
	Response res = when()
	.get("/projects");//terminate
	
	boolean flag=false;
  List<String> PId = res.jsonPath().get("projectId");
	for(String ProjectId:PId)
	{
		if(ProjectId.equalsIgnoreCase(expdata))
		{
			flag=true;
		}
	}
	Assert.assertTrue(flag);
	System.out.println("Data is Verified");
	
	res.then().log().all();
	
}

@Test
public void staticresponsebodyvalidationinreqres() {
	
	int  expdata=10;
	baseURI="https://reqres.in/";
	
	Response res = when().
	get("/api/users?page=2");//terminate
		
	int actdata=res.jsonPath().get("data[3].id");
	Assert.assertEquals(actdata, expdata);
	System.out.println("Data is verified");
	
	res.then()
	.assertThat()
	.time(Matchers.lessThan(5000l),TimeUnit.MILLISECONDS);
	
}

@Test
public void dynamicResponseBodyinreqres() {
	int expdata=10;
	baseURI="https://reqres.in";
	
	Response res = when().get("/api/users?page=2");//terminate
	
	boolean flag=false;
	List<Integer> ProjId = res.jsonPath().get("data.id");
	for(Integer project:ProjId)
	{
		if(project.equals(expdata))
		{
			flag=true;
		}
	}
	Assert.assertTrue(flag);
	System.out.println("Data is Verified");
	res.then().log().all();
	
}

@Test
public void requestChaining() {
	int ran = new Random().nextInt(1000);
	baseURI="http://rmgtestingserver";
	port=8084;
	JSONObject jobj = new JSONObject();
	jobj.put("createdBy","Heshika"+ran);
	jobj.put("projectName","Proj_No001"+ran);
	jobj.put("status","ongoing");
	jobj.put("teamSize",10);
	
	Response res = given()
	.body(jobj)
	 .contentType(ContentType.JSON)
	  
	  .when()
	  .post("/addProject");
	  
	String pid=res.jsonPath().get("projectId");
	System.out.println(pid);
	 
	 res.then()
	  .assertThat()
	  .statusCode(201)
	  .contentType(ContentType.JSON)
	  .time(Matchers.lessThan(5000l),TimeUnit.MILLISECONDS)
	  .log().all();
	
	  given().pathParam("PId", pid)
	  .when().get("/projects/{PId}")
	  .then().log().all();
}

@Test
public void reqres() {
	int ran = new Random().nextInt(1000);
	baseURI="https://reqres.in/";
//	JSONObject jobj = new JSONObject();
//	jobj.put("name", "Heshikagowda"+ran);
//	jobj.put("job", "leader"+ran);
//	
//	Response res = given()
//	.body(jobj)
//	.contentType(ContentType.JSON)
//	
	Response res = when()
	.get("/api/users?page=2");
	
	String pid = res.jsonPath().getString("data[0].id");
	System.out.println(pid);
	
	res.then()
	.assertThat()
	.statusCode(200)
	.contentType(ContentType.JSON)
	.time(Matchers.lessThan(5000l),TimeUnit.MILLISECONDS)
	.log().all();
	
	
	given().pathParam("PId", pid)
	.when().get("/api/users/{PId}")
	.then().log().all();
	
}

@DataProvider

	public Object[][] dataprovider(){
		Object[][] obj = new Object[3][4];
		
		obj[0][0]="Heshika";
		obj[0][1]="PROJ_001";
		obj[0][2]="Completed";
		obj[0][3]=12;
		
		obj[1][0]="Hithiksha";
		obj[1][1]="PRJ_002";
		obj[1][2]="Created";
		obj[1][3]=10;
		
		obj[2][0]="Jashwanth";
		obj[2][1]="PROJ_003";
		obj[2][2]="On Going";
		obj[2][3]=11;
		return obj;
	}

@Test(dataProvider = "dataprovider")
public void Exe(String createdBy,String projectName,String status,int teamSize ) {
	
	int ran = new Random().nextInt(1000);
	baseURI="http://rmgtestingserver";
	port=8084;
	pojoclass poj = new pojoclass(createdBy, projectName+ran, status, teamSize);
	given()
	.body(poj)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/addProject")
	
	.then().log().all();
	
}

@DataProvider
	public Object[][] reqresdata(){
		Object[][] obj = new Object[2][2];
		obj[0][0]="Heshika";
		obj[0][1]="leader";
		
		obj[1][0]="Yash";
		obj[1][1]="HR";
		return obj;
	}



@Test(dataProvider = "reqresdata")
public void exereqres(String name,String job) {
	int ran = new Random().nextInt(1000);
	baseURI="https://reqres.in/";
	
	pojoclass1 poj = new pojoclass1(name+ran, job);
	given()
	.body(poj)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/api/users")
	
	.then().log().all();
}


}



