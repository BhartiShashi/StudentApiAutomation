/******************************************************
Test Name:Get all employees data
URI: http://dummy.restapiexample.com/api/v1/employees
Request Type: GET
Request Payload(Body): NA

********* Validations **********
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
Content Length <800
 *********************************************************/

package com.studentapi.testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.studentapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC001_Get_All_Students extends TestBase{

		
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		System.out.println("going to fetch all student record");
	
	logger.info("*********Started TC001_Get_All_Employees **********");
		
	RestAssured.baseURI = "http://localhost:8080";
	httpRequest = RestAssured.given();
	response = httpRequest.request(Method.GET,"/list");

		System.out.println("fetched all student record"+ response);
	
	Thread.sleep(5);
	}


			
	@Test
	void checkResposeBody()
	{
		logger.info("***********  Checking Respose Body **********");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>"+responseBody);
		System.out.println("!!!!!!!!!!"+responseBody);
		Assert.assertTrue(responseBody!=null);
		
	}
		
	@Test
	void checkStatusCode()
	{
		logger.info("***********  Checking Status Code **********");
		
		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status Code is ==>" + statusCode); //200
		System.out.println("!!!!!!!!!!"+statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
		
	@Test
	void checkResponseTime()
	{
		logger.info("***********  Checking Response Time **********");
		
		long responseTime = response.getTime(); // Getting status Line
		logger.info("Response Time is ==>" + responseTime);
		System.out.println("!!!!!!!!!!"+responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		
		Assert.assertTrue(responseTime<10000);
		
			
	}
	

	
//	@Test
//	void checkContentType()
//	{
//		logger.info("***********  Checking Content Type **********");
//
//		String contentType = response.header("Content-Type");
//		System.out.println("!!!!!!!!!!"+contentType);
//		logger.info("Content type is ==>" + contentType);
//		Assert.assertEquals(contentType, "application/json;charset=UTF-8");
//	}



	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC001_Get_All_Employees **********");
	}
				 	
}
