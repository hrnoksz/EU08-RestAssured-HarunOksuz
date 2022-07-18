package com.cydeo.day04;

import com.cydeo.utililities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSAPİWithJasonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        //get the second country name with JsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");

        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);
        for (String eachCountryId : allCountryIds) {
            System.out.println("eachCountryId = " + eachCountryId);
        }

        //get all country names where their region id is equal to 2 !!!!!!!!!!!!!!!!!!!!!!!
        List<Object> countryNameWithRegion2 = jsonPath.getList("items.findAll {it.region_id == 2}.country_name");
        System.out.println("countryNameWithRegion2 = " + countryNameWithRegion2);

    }

    @DisplayName("GET request to /employees with query param")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit", "107")
                .when().get("/employees");

        //get me all email of employees who is working as IT_PROG
        JsonPath jsonPath = response.jsonPath();

        List<String> emailList = jsonPath.getList("items.findAll {it.job_id ==\"IT_PROG\"}.email");
        System.out.println("emailList = " + emailList);

        //get me first name of employees who is making more than 10000
        List<String> firstnameList = jsonPath.getList("items.findAll {it.salary > 10000}.first_name");
        System.out.println("firstnameList = " + firstnameList);

        //get me first_name who is making the max salary
        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);

        String kingNameWithPathMethod  = response.path("items.max{it.salary}.first_name");
        System.out.println("kingNameWithPathMethod = " + kingNameWithPathMethod);

    }
}
