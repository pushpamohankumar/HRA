package reqres;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class createwithoutbdd {
@Test
public void createproject() {
	
	JSONObject jobj = new JSONObject();
	jobj.put("name", "heshika");
	jobj.put("job","leader");
	
	
	RequestSpecification req = RestAssured.given();
	req.body(jobj);
	req.contentType(ContentType.JSON);
	
	Response res = req.post("https://reqres.in/api/users");
	System.out.println(res.prettyPeek());
}

@Test
public void getallproject()
{
	Response res = RestAssured.get("https://reqres.in/api/users?page=2");
	System.out.println(res.prettyPeek());
}
@Test
public void getsingleuser() {
	Response res = RestAssured.get("https://reqres.in/api/users/2");
	System.out.println(res.prettyPeek());
}
@Test
public void compleupdate() {
	
	JSONObject jobj = new JSONObject();
	jobj.put("name", "yash");
	jobj.put("job","leader");
	
	RequestSpecification req = RestAssured.given();
	req.body(jobj);
	req.contentType(ContentType.JSON);
	
	Response res = req.put("https://reqres.in/api/users/2");
	System.out.println(res.prettyPeek());
	int resp = res.statusCode();
	Assert.assertEquals(200, resp);
	System.out.println("AS EXPECTED");
}

}