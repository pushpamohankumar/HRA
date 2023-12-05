package authendication;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoclassPackage.pojoclass;

import static io.restassured.RestAssured.*;

import java.util.Random;

public class authendications {
@Test
public void basicAuth() {
	
	baseURI="http://rmgtestingserver";
	port=8084;
	given()
	.auth().basic("rmgyantra", "rmgy@9999")
	.when().get("/login")
	.then().assertThat().statusCode(202)
	.contentType(ContentType.JSON)
	.log().all();
}

@Test
public void preemptiveAuth() {
	baseURI="http://rmgtestingserver";
	port=8084;
	given()
	.auth().preemptive().basic("rmgyantra", "rmgy@9999")
	.when().get("/login")
	.then().assertThat().statusCode(202).contentType(ContentType.JSON)
	.log().all();
	
}

@Test
public void digestiveAuth() {
	baseURI="http://rmgtestingserver";
	port=8084;
	given()
	.auth().digest("rmgyantra", "rmgy@9999")
	.when().get("/login")
	.then().assertThat().statusCode(202).contentType(ContentType.JSON)
	.log().all();
}

@Test

public void bearerToken() {
	baseURI="https://api.github.com";
	JSONObject jobj = new JSONObject();
	jobj.put("name","RestAssured");
	jobj.put("description", "RestAssured");
	given()
	.auth()
	.oauth2("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")
	.body(jobj)
	.contentType(ContentType.JSON)
	
	.when().post("/user/repos")
	.then()
	.assertThat().statusCode(201)
	.log().all();
	
}

@Test
public void oauthAuth() {
	Response res = given()
	.formParam("client_id", "sdet_51")
	.formParam("client_secret", "3a693661c11e4a3d068710b6ae8e5701")
	.formParam("grant_type","client_credentials")
	.formParam("redirect_uri","http://example.com")
	.formParam("code", "authorization_code")
	
	.when().post("http://coop.apps.symfonycasts.com/token");
	
	String token = res.jsonPath().get("access_token");
	System.out.println(token);
	
	given().auth().oauth2(token)
	.pathParam("id", 4752)
	
	.when().post("http://coop.apps.symfonycasts.com/api/{id}/eggs-collect")
	.then().log().all();
}

@Test
public void reqspecbuilderandresponsespecbuild() {
	int ran = new Random().nextInt(1000);
	pojoclass po = new pojoclass("Heshika", "PROJ_001"+ran, "completed", 12);
	 
	RequestSpecification req = new RequestSpecBuilder()
	 .setBaseUri("http://rmgtestingserver:8084")
	  .setContentType(ContentType.JSON).build();
	  
	ResponseSpecification res = new ResponseSpecBuilder()
	  .expectContentType(ContentType.JSON)
	  .expectStatusCode(201).build();
	
	given().spec(req).body(po)
	.when().post("/addProject")
	.then().spec(res).log().all();
}
}
