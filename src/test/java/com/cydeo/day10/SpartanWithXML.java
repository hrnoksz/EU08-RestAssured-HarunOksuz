package com.cydeo.day10;

import com.cydeo.utililities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanWithXML extends SpartanAuthTestBase {

    @DisplayName("GET request to /api/spartans and verify xml")
    @Test
    public void getSpartanXml(){
        //we will ask for xml response
        //assert status code 200
        //assert content type is xml (we got xml response)
        //verify first spartan name is Meade

         given()
                .accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .when().get("api/spartans")
                .then()
                .statusCode(200)
                 .contentType("application/xml;charset=UTF-8")
                 .body("List.item[0].name", is("Meade"))
                 .body("List.item[0].gender", is("Male"))
                 .log().all();

    }

}
