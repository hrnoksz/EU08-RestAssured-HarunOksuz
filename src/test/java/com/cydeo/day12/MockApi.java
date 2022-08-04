package com.cydeo.day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MockApi {

    //https://d2c37f22-1f84-48f6-86ad-08c73e12c9c0.mock.pstmn.io

    @Test
    public void test1(){

        given().baseUri("https://d2c37f22-1f84-48f6-86ad-08c73e12c9c0.mock.pstmn.io")
                .accept(ContentType.JSON)
        .when()
                .get("/customer")

        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName", is("John"));
    }

    @Test
    public void test2(){

        given().baseUri("https://d2c37f22-1f84-48f6-86ad-08c73e12c9c0.mock.pstmn.io")
                .accept(ContentType.JSON)
        .when()
                .get("/employees")
                .prettyPrint();
    }

}
