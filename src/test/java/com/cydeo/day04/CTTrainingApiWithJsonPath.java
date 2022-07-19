package com.cydeo.day04;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.cydeo.utililities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.baseURI;

public class CTTrainingApiWithJsonPath {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://api.cybertektraining.com";
    }

    @DisplayName("GET Reguest to individiual student")
    @Test
    public void test1() {
        //send a get request to student id 32881 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 32881)
                .when().get("/student/{id}");

        assertEquals(200, response.statusCode());

        assertEquals("application/json;charset=UTF-8", response.contentType());

        //assertTrue(response.headers().hasHeaderWithName("Content-Length"));
        //assertEquals("334", response.header("Content-Length"));

        //assertEquals("gzip", response.header("Content-Encoding"));

        assertTrue(response.headers().hasHeaderWithName("Date"));

        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.getString("students[0].firstName");
        System.out.println("firstName = " + firstName);

        int batch = jsonPath.getInt("students[0].batch");
        System.out.println("batch = " + batch);

        int section = jsonPath.getInt("students[0].section");
        System.out.println("section = " + section);

        String emailAddress = jsonPath.getString("students[0].contact.emailAddress");
        System.out.println("emailAddress = " + emailAddress);

        String companyName = jsonPath.getString("students[0].company.companyName");
        System.out.println("companyName = " + companyName);

        String state = jsonPath.getString("students[0].company.address.state");
        System.out.println("state = " + state);

        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
        System.out.println("zipCode = " + zipCode);

        assertEquals("Vera", firstName);
        assertEquals(14, batch);
        assertEquals(12, section);
        assertEquals("aaa@gmail.com", emailAddress);
        assertEquals("Cybertek", companyName);
        assertEquals("IL", state);
        assertEquals(60606, zipCode);


    }
}
