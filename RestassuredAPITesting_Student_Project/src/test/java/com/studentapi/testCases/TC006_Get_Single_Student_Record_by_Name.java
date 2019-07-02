package com.studentapi.testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.studentapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_Get_Single_Student_Record_by_Name extends TestBase{


    RequestSpecification httpRequest;
    Response response;

    @BeforeClass
    void getEmployeeData() throws InterruptedException
    {
        logger.info("*********Started TC002_Get_Single_Employee_Record **********");

        RestAssured.baseURI = "http://localhost:8080";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/search?id="+empID+"&studentName="+stdName);

        Thread.sleep(7000);
    }

//    @Test
//    void checkResposeBody()
//    {
//        String responseBody = response.getBody().asString();
//        Assert.assertEquals(responseBody.contains(empID), true);
//        System.out.println("log data"+ responseBody);
//    }

    @Test
    void checkStatusCode()
    {
        int statusCode = response.getStatusCode(); // Gettng status code
        Assert.assertEquals(statusCode, 200);
        System.out.println("log data"+ statusCode);
    }

    @Test
    void checkResponseTime()
    {
        long responseTime = response.getTime(); // Getting status Line
        Assert.assertTrue(responseTime<6000);
        System.out.println("log data"+ responseTime);

    }


//    @Test
//    void checkContentType()
//    {
//        String contentType = response.header("Content-Type");
//        Assert.assertEquals(contentType, "application/json;charset=UTF-8");
//        System.out.println("log data"+ contentType);
//    }


    @AfterClass
    void tearDown()
    {
        logger.info("********* Finished TC002_Get_Single_Employee_Record **********");
    }

}


