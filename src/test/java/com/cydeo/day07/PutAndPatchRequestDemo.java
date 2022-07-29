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

        /*
        When we use PUT request, we should provide all data's inside JSON body.
        It doesn't matter whether we update or not them.
        In following example, we update only phone number, but we provide all data's
         */

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
    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void patchRequest() {
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone", 8811111111L);

        /*
        When we use PATCH request, we don't need to provide all data's.
        We provide only data's that we only update them.
        It is possible to update more than one data
         */

        given().contentType(ContentType.JSON)
                .body(putRequestMap).log().all()
                .and().pathParam("id", 117)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send
       Spartan spartan = given().accept(ContentType.JSON)
                .and().pathParam("id", 117)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response().as(Spartan.class);
       assertThat(putRequestMap.get("phone"), is(spartan.getPhone()));

    }
    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){
        try{
            int idToDelete = 112;

            given().pathParam("id", idToDelete)
                    .when().delete("/api/spartans/{id}")
                    .then().statusCode(204);
        }catch (Exception e){
            e.printStackTrace();
        }

        //send a get request after you delete make sure you are getting 404
        given().pathParam("id", 112)
                .when().get("api/spartans")
                .then().statusCode(404);
    }

}
