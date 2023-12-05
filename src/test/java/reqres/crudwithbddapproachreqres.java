package reqres;

import org.json.simple.JSONObject;
import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class crudwithbddapproachreqres {
@Test
public void createpoject()
{
	baseURI="https://reqres.in/";
	JSONObject jobj = new JSONObject();
	jobj.put("name", "Heshikagowda");
	jobj.put("job", "leader");
	
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
public void getAllProject()
{
	baseURI="https://reqres.in/";
	when()
	.get("/api/users/2")
	
	.then()
	.assertThat()
	.statusCode(200)
	.contentType(ContentType.JSON)
	.log().all();
}

@Test
public void completeupdation()
{
	baseURI="https://reqres.in/";
	
	JSONObject jobj = new JSONObject();
	jobj.put("name", "jashwanth");
	jobj.put("job", "leader");
	
	given()
	.body(jobj)
	.contentType(ContentType.JSON)
	
	.when()
	.put("/api/users/2")
	
	.then()
	.assertThat()
	.statusCode(200)
	.contentType(ContentType.JSON)
	.log().all();
}

@Test
public void getSingleproject()
{
	baseURI="https://reqres.in/";
	when()
	.get("/api/users/2")
	
	.then()
	.assertThat()
	.statusCode(200)
	.contentType(ContentType.JSON)
	.log().all();
}
}
