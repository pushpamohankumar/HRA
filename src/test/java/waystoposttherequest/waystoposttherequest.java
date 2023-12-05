package waystoposttherequest;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

public class waystoposttherequest {
@Test
public void jsonobj()
{
	baseURI="http://rmgtestingserver";
	port=8084;
	JSONObject jobj = new JSONObject();
	jobj.put("createdBy","Heshika");
	jobj.put("projectName","PrOJhytd01");
	jobj.put("status","ongoing");
	jobj.put("teamSize",10);
	
	given()
	.body(jobj)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/addProject")
	
	.then()
	.assertThat()
	.statusCode(201)
	.contentType(ContentType.JSON)
	.log().all();
	
}

@Test
public void jsonfile()
{
	int ran = new Random().nextInt(1000);
	baseURI="http://rmgtestingserver";
	port=8084;
	File file = new File("./src/test/resources/sedt_51.json");
	
	given()
	.body(file)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/addProject")
	
	.then()
	.assertThat()
	.statusCode(201)
	.contentType(ContentType.JSON)
	.log().all();
}

@Test
public void hashmap() {
	int ran = new Random().nextInt(1000);
	baseURI="http://rmgtestingserver";
	port=8084;
	HashMap<String, Object> map=new HashMap<String, Object>();
	map.put("createdBy","Heshikagowdam"+ran);
	map.put("projectName","PrOJ01345"+ran);
	map.put("status","ongoing14");
	map.put("teamSize",15);
	
	given()
	.body(map)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/addProject")
	
	.then()
	.assertThat()
	.statusCode(201)
	.contentType(ContentType.JSON)
	.log().all();
}
@Test
public void pojoclass() {
	int ran = new Random().nextInt(1000);
	baseURI="http://rmgtestingserver";
	port=8084;
	pojoclassPackage.pojoclass p = new pojoclassPackage.pojoclass("HeshikaGowda01"+ran, "projct_001"+ran, "completed",15);
	
	given()
	.body(p)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/addProject")
	
	.then()
	.assertThat()
	.statusCode(201)
	.contentType(ContentType.JSON)
	.log().all();

}
}
