/******************************************************
Test Name: Delete an employee record
URI: http://dummy.restapiexample.com/api/v1/delete/{id}
Request Type: DELETE
Request Payload(Body): NA

********* Validations **********
Response Payload(Body) : {"success":{"text":"successfully! deleted Records"}}
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
**********************************************************/

package com.studentapi.testCases;

//import jdk.nashorn.internal.runtime.regexp.joni.Regex;
//import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.annotations.*;

import com.studentapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class TC005_Delete_Student_Record extends TestBase{

	RequestSpecification httpRequest;
	Response response;
		
	@BeforeClass
	void deleteEmployee() throws InterruptedException
	{
		logger.info("*********Started TC005_Delete_Student_Record **********");
		
		RestAssured.baseURI = "http://localhost:8080";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET, "/list");
		System.out.println("!!!! response stored " +response);
				
		// First get the JsonPath object instance from the Response interface
		//JsonPath jsonPathEvaluator = response.jsonPath();
			 
		//Capture id
		//String empID=jsonPathEvaluator.get("[0].id");
		String responseBody = response.getBody().asString();
		System.out.println("delete record for id "+ responseBody);

		String empID=responseBody.substring(7,12);
		String stdID= empID.replaceAll("[^0-9]", "");
		System.out.println("id  "+empID);
		System.out.println("id  "+stdID);
		response = httpRequest.request(Method.DELETE, "/delete/"+stdID); //Pass ID to delete record
		
		Thread.sleep(3000);
	}
	
//	@Test
//	void checkResposeBody()
//	{
//		String responseBody = response.getBody().asString();
//		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
//		System.out.println("log data"+ responseBody);
//
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
		logger.info("*********  Finished TC005_Delete_Student_Record **********");
	}
}
