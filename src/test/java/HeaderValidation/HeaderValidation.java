package HeaderValidation;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.response.Response;


public class HeaderValidation {

	@Test
	public void headervalidation() {
	
		baseURI="http://rmgtestingserver";
		port=8084;
	String exp_statusline="HTTP/1.1 200 ";
	String exp_vary="Access-Control-Request-Method";
	String exp_contenttype="application/json";
	String exp_Pragma=" no-cache";
	String exp_Connection="keep-alive";
	
	Response res = when().get("/projects/TY_PROJ_1389");
	
	String statusline = res.statusLine();
	System.out.println(statusline);
	Assert.assertEquals(statusline, exp_statusline);
	
	
	
	Headers headers = res.getHeaders();
	System.out.println(headers);
	
	String actual_contentType = res.getHeader("Content_Type");
	Assert.assertEquals(actual_contentType, exp_contenttype);
	
	String act_pragma = res.getHeader("pragma");
	Assert.assertEquals(act_pragma, exp_Pragma);
	
	
	String act_Connection = res.getHeader("Connection");
	Assert.assertEquals(act_Connection, exp_Connection);
}
}