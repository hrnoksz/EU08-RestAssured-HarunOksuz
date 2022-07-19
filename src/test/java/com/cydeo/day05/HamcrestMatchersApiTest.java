package com.cydeo.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HamcrestMatchersApiTest {

    /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1() {

                given().
                    accept(ContentType.JSON)
                    .and().pathParam("id", 15)
                .when()
                    .get("http://3.83.123.243:8000/api/spartans/{id}")
                .then()
                    .statusCode(200)
                    .and().assertThat()
                    .contentType("application/json")
                    .and()
                    .body("id", is(15),
                        "name", is("Meta"), "gender", is("Female"),
                        "phone", is(1938695106));

    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void test2() {

                given()
                    .accept(ContentType.JSON)
                    .and().pathParam("id", 21887)
                .when()
                    .get("http://api.cybertektraining.com/teacher/{id}")
                .then()
                    .statusCode(200).and()
                    .contentType("application/json")
                    .and()
                    //.header("Content-Length", is("275")) There is a problem related to website
                    .and()
                    .header("Date", notNullValue())
                    .and().assertThat()
                    .body("teachers[0].firstName", is("Leonel"))
                    .body("teachers[0].lastName", is("Messi"))
                    .body("teachers[0].gender", is("Male"));
    }
}
