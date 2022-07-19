package com.cydeo.day05;

import com.cydeo.utililities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET request to Employees IT_PROG and chaining")
    @Test
    public void employeesTest(){
        //send a get request to emplyoees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)

        //expected names
        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

                given().
                    accept(ContentType.JSON)
                    .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                    .get("/employees")
                .then()
                    .statusCode(200)
                    .body("items.job_id", everyItem(equalTo("IT_PROG")))
                    .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"))
                    .body("items.email",containsInAnyOrder("VPATABAL","DAUSTIN","BERNST","AHUNOLD","DLORENTZ"))
                    .body("items.first_name", equalTo(names)); //equality of lists assertion
        //containsInRelativeOrder() method --> contains with order
        //containsInAnyOrder() method --> contains without order

    }

    @Test
    public void employeeTest2(){
        //We want to chain and also get response object
        //After chaining we use extract() method to get response object!!!!!!!!!!!!!!!!!!!!!!!
       Response response = given().
                accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().response();

       response.prettyPrint();

       //extract() method allows us to get jsonPath object
       JsonPath jsonPath = given().
                accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();

       //assert that we have only 5 firstnames
       assertThat(jsonPath.getList("items.first_name"), hasSize(5));

       //assert firstnames order
        assertThat(jsonPath.getList("items.first_name"), containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));
    }



}
