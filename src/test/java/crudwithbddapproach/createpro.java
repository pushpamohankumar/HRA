package crudwithbddapproach;

import  org.testng.annotations.Test;


import org.json.simple.JSONObject;

import static io.restassured.RestAssured.*;

import java.util.Random;

import io.restassured.http.ContentType;

public class createpro {
@Test
public void createProject()
{
	int ranInt = new Random().nextInt(1000);
	baseURI="http://rmgtestingserver";
	port=8084;
	JSONObject jobj = new JSONObject();
	jobj.put("createdBy","Heshikagowda"+ranInt);
	jobj.put("projectName","project_0012"+ranInt);
	jobj.put("status","created");
	jobj.put("teamSize",15);
	
	
	//Pre Condition
	given()
	.body(jobj)
	.contentType(ContentType.JSON)
	
	//actions
	.when()
	.post("/addProject")
	
	//validate
	
	.then()
	.assertThat()
	.statusCode(201)
	.contentType(ContentType.JSON)
	.log().all();
	
}
@Test
public void gerproject() {
	baseURI="http://rmgtestingserver";
	port=8084;
	when()
	.get("/projects")
	
	.then()
	.assertThat()
	.statusCode(200)
	.contentType(ContentType.JSON)
	.log()
	.all();
}

@Test
public void completeupdation() {
	
	baseURI="http://rmgtestingserver";
	port=8084;
	//int ranInt = new Random().nextInt(1000);
	JSONObject jobj = new JSONObject();
	
	jobj.put("createdBy","jashwanth");
	jobj.put("projectName","project_0018");
	jobj.put("status","completed");
	jobj.put("teamSize",25);
	
	
	given()
	.body(jobj)
	.contentType(ContentType.JSON)
	
	.when()
	.put("/projects/TY_PROJ_1526")
	
	.then()
	.assertThat()
	.statusCode(200)
	.contentType(ContentType.JSON)
	.log().all();
	
}
@Test
public void getSingle()
{
	baseURI="http://rmgtestingserver";
	port=8084;
	when()
	.get("/projects/TY_PROJ_1526")
	
	.then()
	.assertThat()
	.statusCode(200)
	.contentType(ContentType.JSON)
	.log().all();
	
}
}
