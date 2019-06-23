/******************************************************
Test Name:Create new record in database 
URI: http://dummy.restapiexample.com/api/v1/create
Request Type: POST
Request Payload(Body): {"name":"XXXXX","salary":"XXXX","age":"XX"}

********* Validations **********
Response Payload(Body) : {"name":"XXXXX","salary":"XXXX","age":"XX"}
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
**********************************************************/

package com.studentapi.testCases;

import org.json.JSONException;
import org.json.JSONObject;

import org.testng.Assert;
import org.testng.annotations.*;

import com.studentapi.base.TestBase;
import com.studentapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Student_Record extends TestBase{

	RequestSpecification httpRequest;
	Response response;
	
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	
	@BeforeClass
	void createEmployee() throws InterruptedException, JSONException {
		logger.info("*********Started TC003_Post_Employee_Record **********");
		
		RestAssured.baseURI = "http://localhost:8080";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method
		//{"name":"John123X","salary":"123","age":"23"}
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName); // Cast
		requestParams.put("id", empSalary);
		requestParams.put("roll", empAge);
		
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		httpRequest.body(requestParams.toString());

		response = httpRequest.request(Method.POST, "/create");
		
		Thread.sleep(5000);

	}
	
//	@Test
//	void checkResposeBody()
//	{
//		String responseBody = response.getBody().asString();
//		Assert.assertEquals(responseBody.contains(empName), true);
//		Assert.assertEquals(responseBody.contains(empSalary), true);
//		Assert.assertEquals(responseBody.contains(empAge), true);
//		System.out.println("log data"+ responseBody);
//	}
		
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
		System.out.println("log data"+ statusCode);
	}
		

	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC003_Post_Employee_Record **********");
	}

}
