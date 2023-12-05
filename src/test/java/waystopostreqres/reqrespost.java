package waystopostreqres;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import pojoclassPackage.pojoclass1;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

public class reqrespost {
@Test
public void createproject() {
	int ran = new Random().nextInt(1000);
	baseURI="https://reqres.in/";
	JSONObject jobj = new JSONObject();
	jobj.put("name", "Heshikagowda"+ran);
	jobj.put("job", "leader"+ran);
	
	given()
	.body(jobj)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/api/users")
	
	.then()
	.assertThat()
	.statusCode(201)
	.contentType(ContentType.JSON)
	.log().all();
}

@Test
public void jsonfile() {
	int ran = new Random().nextInt(1000);
	baseURI="https://reqres.in/";
	
	File fil = new File("./src/test/resources/reqres.json");
	given()
	.body(fil)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/api/users")
	
	.then()
	.assertThat()
	.statusCode(201)
	.contentType(ContentType.JSON)
	.log().all();
	
}

@Test
public void hashmap() {
	baseURI="https://reqres.in/";
	HashMap<String, Object> map=new HashMap<String, Object>();
	map.put("name","JashwanthGowda");
	map.put("job","leader");
	
	given()
	.body(map)
	.contentType(ContentType.JSON)
	
	.when()
	.post("/api/users")
	
	.then()
	.assertThat()
	.statusCode(201)
	.contentType(ContentType.JSON)
	.log().all();
	
}
@Test
public void pojoclass() {
	baseURI="https://reqres.in/";
	pojoclass1 pojo = new pojoclass1("jashwanth1", "leader");
	
}
}
