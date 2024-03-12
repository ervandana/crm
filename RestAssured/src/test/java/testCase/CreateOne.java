package testCase;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateOne {

	String baseUri;
	String fileName;
	String username;
	String password;
	String incidentId;

	public CreateOne() {

		baseUri = "https://dev182000.service-now.com/api/now/table";
		fileName = "src\\main\\java\\Create.json";
		username = "admin";
		password = "@2@rEJsyJNf8";
	}

	@Test (priority = 1)
	public void createOneIncident() {

		Response response =

				given().baseUri(baseUri)

						.auth().basic(username, password).header("Content-Type", "application/json")
						.body(new File(fileName)).
				when()
						.post("incident").

			    then().extract().response();

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 201);

		String responseBody = response.getBody().asString();
		System.out.println("Response Body:" + responseBody);

		JsonPath jp = new JsonPath(responseBody);
		String incidentMessage = jp.get("message");
		Assert.assertEquals(incidentMessage, "Incident is created", "Product Message is not matching!");

	}
	
	
    @Test (priority = 2)
	public void getAllIncidents() {

		Response response = 
		given()
			.baseUri("https://dev182000.service-now.com/api/now/table").auth().basic("admin", "@2@rEJsyJNf8")
				.accept("application/json").
		when()
			.get("incident").
		then()
			.extract().response();
		
		int statusCode = response.getStatusCode();
		System.out.println("Status Code:" + statusCode);
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body:" + responseBody);

		JsonPath jp1 = new JsonPath(responseBody);
		String incidentShortDescription = jp1.get("result[48].short_description");
		System.out.println("New created incident short description : " + incidentShortDescription );
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(incidentShortDescription, "I need a software setup on Eclipse", "Incident Captured");
		
		incidentId = jp1.get("result[48].sys_id");
		System.out.println("Incident id : " + incidentId);

	}
	
    @Test (priority = 3)
    public void getOneIncident() {
		
        Response response = 
				
		given()
			.baseUri(baseUri)
			.auth().basic(username, password)
			.accept("application/json")
			.queryParam("sys_id", incidentId).
			
		when()
			.get("incident").
			
		then()
		.extract().response();
		
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		
	    String number = jp.get("result[0].number");
	    System.out.println(number);
	    
	    String priority = jp.get("result[0].priority");
	    System.out.println(priority);
	    
	    String desc = jp.get("result[0].short_description");
	    System.out.println(desc);
		
    }	

}