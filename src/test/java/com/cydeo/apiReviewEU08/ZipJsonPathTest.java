package com.cydeo.apiReviewEU08;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


public class ZipJsonPathTest extends ZipBase{

    /*
Exercise with JsonPath Method
Given Accept application/json
And "city" path is ma/belmont
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
   post codes are existing : "02178","02478"
   country  is United States
   state abbreviation is MA
   place name is Belmont
   state is Massachusetts
   latitudes are 42.4464,42.4128
     */

    @Test
    public void jsonPathTest(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("state", "ma")
                .and().pathParam("city", "belmont")
                .when()
                .get("/{state}/{city}");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.prettyPrint());

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals("cloudflare", response.header("Server"));
        assertTrue(response.headers().hasHeaderWithName("Report-To"));
        assertEquals("[02178, 02478]", jsonPath.getString("places.\'post code\'"));
        assertEquals("United States", jsonPath.getString("country"));
        assertEquals("MA", jsonPath.getString("\'state abbreviation\'"));
        assertEquals("Belmont", jsonPath.getString("\'place name\'"));
        assertEquals("Massachusetts", jsonPath.getString("state"));
        assertEquals("[42.4464, 42.4128]", jsonPath.getString("places.latitude"));

        // verify "post code": "02178"  that  "latitude": "42.4464"
        // GPATH syntax comes with "it statement": use it like a coding algorithm!!!!!!!!!!!!!!!!!!!!!!
        String expectedLatitude = "[42.4464]";
        String actualLatitude  = jsonPath.getString("places.findAll {it.\'post code\'==\"02178\"}.latitude");
        assertEquals(expectedLatitude,actualLatitude);
        System.out.println("actualLatitude = " + actualLatitude);

    }
}
