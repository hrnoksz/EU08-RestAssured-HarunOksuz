package com.cydeo.day08;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class BookItTest {

    @BeforeAll
    public static void init() { //MUST BE STATIC!!!!!!!!!!!!!!!!
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";

    }
    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzkiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0._vM1-eRoS7SsHu6T-QPdJoEdA8LSwnxUvvTTbhV-8ms";

    @DisplayName("GET all campuses")
    @Test
    public void testAuth1(){

        given().header("Authorization", accessToken)
                .and().accept(ContentType.JSON)
                .when()
                .get("api/campuses")
                .then()
                .statusCode(200)
                .log().all();
    }
}
