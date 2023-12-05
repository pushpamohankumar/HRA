
package com.createwithoutbdd;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class postrepo {
@Test
public void createproject() {
	
	JSONObject jobj = new JSONObject();
	jobj.put("createdBy","Heshika");
	jobj.put("ProjectName","proj_001");
	jobj.put("status","created");
	jobj.put("teamsize",10);
	
	
	RequestSpecification re = RestAssured.given();
	re.body(jobj);
	re.contentType(ContentType.JSON);
	
	Response res = re.post("http://rmgtestingserver:8084/addProject");
	//System.out.println(res.asString());
	System.out.println(res.prettyPeek());
	//System.out.println(res.prettyPrint());
	//System.out.println(res.contentType());
}

@Test
public void getProject() {
	Response res = RestAssured.get("http://rmgtestingserver:8084/projects");
	System.out.println(res.prettyPeek());
}

@Test
public void completeupdate()

{
	JSONObject jobj= new JSONObject();
	jobj.put("createdBy","Heshika");
	jobj.put("ProjectName","proj_001");
	jobj.put("status","created");
	jobj.put("teamsize",15);
	
	RequestSpecification re = RestAssured.given();
	re.body(jobj);
	re.contentType(ContentType.JSON);
	
	Response res = re.put("http://rmgtestingserver:8084/projects/TY_PROJ_1389");
	System.out.println(res.prettyPeek());
}

@Test
public void delete() {
	Response res = RestAssured.delete("http://rmgtestingserver:8084/projects/{projectId}");
	System.out.println(res.asString());
}
@Test
public void getSingleProject()
{
	Response res = RestAssured.get("http://rmgtestingserver:8084/projects/TY_PROJ_1389");
	System.out.println(res.prettyPeek());
}
}
