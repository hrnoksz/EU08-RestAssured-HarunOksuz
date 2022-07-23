package com.cydeo.apiReviewEU08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZipTestWithCollection extends ZipBase {

    @Test
    public void collectionTest(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when()
                .get("/{zip}");
        response.prettyPrint();
    //System.out.println("response.asString() = " + response.asString());
        /*
    {
    "post code": "22031",
    "country": "United States",
    "country abbreviation": "US",
    "places": [
          {
            "place name": "Fairfax",
            "longitude": "-77.2649",
            "state": "Virginia",
            "state abbreviation": "VA",
            "latitude": "38.8604"
           }
    ]
    }
         */
        //De-serialization!!!!!!!!!!!!!!!!!

        Map<String, Object> postCode = response.body().as(Map.class);
        System.out.println("postCode.get(\"post code\") = " + postCode.get("post code"));

        assertEquals("22031", postCode.get("post code"));
        assertEquals("United States", postCode.get("country"));

        //"state" : "Virginia" verify; this information is inside the "places" key
        List<Map<String, Object>> places = (List<Map<String, Object>>) postCode.get("places");
        assertEquals("Virginia", places.get(0).get("state"));

        assertEquals("VA", places.get(0).get("state abbreviation"));

        assertEquals("38.8604", places.get(0).get("latitude"));

        assertEquals("Fairfax", places.get(0).get("place name"));
    }
}
