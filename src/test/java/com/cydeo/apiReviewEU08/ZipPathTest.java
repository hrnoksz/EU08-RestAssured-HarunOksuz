package com.cydeo.apiReviewEU08;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ZipPathTest extends ZipBase {

    /*
    Exercise with Path Method
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
   post code is 22031
   country  is United States
   country abbreviation is US
   place name is Fairfax
   state is Virginia
   latitude is 38.8604
    */

    @Test
    public void pathTest(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when()
                .get("/{zip}"); //endpoint is provided from ZipBase class
        response.prettyPrint();

        //No matter which approach you can verify these parts (status code, content type and
        // headers with response object ready methods

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.header("Server").equalsIgnoreCase("cloudflare"));

        /*
        Headers headers = response.getHeaders();
        for (Header header : headers) {
            System.out.println(header.getName());
        }

         */
        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        //from this part we will use path method for BODY verification
        assertEquals("22031", response.path("\'post code\'"));
        //if there is two words (post code) we use single quotation!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        assertEquals("United States", response.path("country"));
        assertEquals("US", response.path("\'country abbreviation\'"));
        assertEquals("Fairfax", response.path("places[0].\'place name\'"));
        assertEquals("Virginia", response.path("places[0].state"));
        assertEquals("38.8604", response.path("places[0].latitude"));

    }
}
