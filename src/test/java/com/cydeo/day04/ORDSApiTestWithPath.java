package com.cydeo.day04;

import com.cydeo.utililities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print count
        System.out.println("response.path(\"count\") = " + response.path("count"));

        //We consider every {} JSON element in Array count as ındex and index starts from zero!!!!!!!!!!!!!!!!!!
        //print first country_id
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print "href": "http://3.83.123.243:1000/ords/hr/countries/CA"
        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("href = " + thirdHref);

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println(countryNames);
        for (String each : countryNames) {
            System.out.println(each);
        }

        //get me all the country_id
        List<String> countryIds = response.path("items.country_id");
        System.out.println(countryIds);
        for (String eachCountryId : countryIds) {
            System.out.println(eachCountryId);
        }

        //assert that all regions ids are equal to
        List<Integer> allRegionIds = response.path("items.region_id");
        for (Integer eachAllRegionId : allRegionIds) {
            System.out.println("eachAllRegionId = " + eachAllRegionId);
            assertEquals(2, eachAllRegionId);
        }

    }


}
