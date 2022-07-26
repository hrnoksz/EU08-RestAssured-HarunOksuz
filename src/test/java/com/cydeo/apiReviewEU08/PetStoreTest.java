package com.cydeo.apiReviewEU08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PetStoreTest {

    String url = "https://petstore.swagger.io/v2";
    @Test
    public void test(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("status", "available")
                .when().get(url+"/pet/findByStatus");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        //response.prettyPrint();


        JsonPath jsonPath = response.jsonPath();

        String actualName = jsonPath.getString("name[0]");
        System.out.println("actualName = " + actualName);
    }
}
