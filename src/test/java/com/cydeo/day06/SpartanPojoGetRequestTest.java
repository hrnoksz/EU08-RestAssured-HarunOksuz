package com.cydeo.day06;

import com.cydeo.day06.pojo.Spartan;
import com.cydeo.utililities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("Get one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("api/spartans/{id}")
                .then().statusCode(200)
                .extract().response();

        //Deserialize --> JSON to POJO (java custom class)
        // 2 different ways
        // 1. using as() method
        //we use as() method to convert Json response to spartan object!!!!!!!!!!!!!!
        //as() method is coming from jackson library (converting JSON to Java class)
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());

        //second way of deserialize JSON to java
        //2. using JsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("", Spartan.class); //Magical line!!!!!!!!!!!!!!!
        System.out.println(s15);
        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getPhone() = " + s15.getPhone());
    }

}
