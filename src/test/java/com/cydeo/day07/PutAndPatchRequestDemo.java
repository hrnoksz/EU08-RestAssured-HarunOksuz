package com.cydeo.day07;

import com.cydeo.day06.pojo.Spartan;
import com.cydeo.utililities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void putRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "Severus");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 8811111111L);

       given().contentType(ContentType.JSON)
                .body(putRequestMap).log().all()
                .and().pathParam("id", 117)
                .when().put("/api/spartans/{id}")
        .then()
               .statusCode(204);


        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send

        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", 117)
                .when().get("api/spartans/{id}")
                .then().statusCode(200).extract().response().as(Spartan.class);

        assertThat(putRequestMap.get("phone"), is(spartanPosted.getPhone()));

    }


}
