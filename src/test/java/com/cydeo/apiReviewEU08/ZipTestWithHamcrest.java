package com.cydeo.apiReviewEU08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static  org.hamcrest.Matchers.*;

public class ZipTestWithHamcrest extends ZipBase{

    @Test
    public void hamcrestTest(){

        given().log().all().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get("/{zip}")
                .then()
                .statusCode(200)
                .and().header("Server", equalTo("cloudflare"))
                .header("Report-To", notNullValue())
                .and().assertThat()
                .body("country", is("United States"),
                        "'post code'", equalTo("22031"),
                        "places[0].state", equalTo("Virginia"),
                        "'country abbreviation'", is("US"),
                        "places[0].'place name'", is("Fairfax"),
                        "places[0].latitude", equalTo("38.8604"));




        //to verify this statement without assigning to a response we use Hamcrest Matchers library
    }
}
