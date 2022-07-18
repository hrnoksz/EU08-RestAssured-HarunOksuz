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
    public void test1(){

        Response response = get("/countries");

        //get the second country name with JsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");

        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        JsonPath jsonPath1 = response.jsonPath();

        List<String> allCountryIds = jsonPath1.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);
        for (String eachCountryId : allCountryIds) {
            System.out.println("allCountryId = " + eachCountryId);
        }

    }


}
