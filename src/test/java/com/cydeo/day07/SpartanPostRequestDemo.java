package com.cydeo.day07;

import com.cydeo.utililities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanPostRequestDemo extends SpartanTestBase {

     /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
    */
    @Test
    public void postMethod1(){

        String requestJsonBody = "{\"gender\":\"Male\",\n" +
                "\"name\":\"Severus\",\n" +
                "\"phone\":8877445596}";
        Response response = given().accept(ContentType.JSON) //what we are asking from API which is JSON response
                .and().contentType(ContentType.JSON) //What we are sending to API, which is JSON also.
                .body(requestJsonBody) //We use body() method to send our request
                .when()
                .post("/api/spartans"); // we use post to put end points

        //verify status code
        assertThat(response.statusCode(), is(201));

        //verify content type
        assertThat(response.contentType(), is("application/json"));

        //verify the message
        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));

        //verify same data is posted
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596L));

    }
    @Test
    public void postMethod2(){

        //create a map to keep request json body information
        Map<String, Object> requestJsonBody = new LinkedHashMap<>();
        requestJsonBody.put("name", "Severus");
        requestJsonBody.put("gender", "Male");
        requestJsonBody.put("phone", "8877445596");

        Response response = given().accept(ContentType.JSON) //what we are asking from API which is JSON response
                .and().contentType(ContentType.JSON) //What we are sending to API, which is JSON also.
                .body(requestJsonBody).log().all() //We use body() method to send our request
                .when()
                .post("/api/spartans"); // we use post to put end points

        //verify status code
        assertThat(response.statusCode(), is(201));

        //verify content type
        assertThat(response.contentType(), is("application/json"));

        //verify the message
        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));

        //verify same data is posted
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596L));

        response.prettyPrint();

    }
}
