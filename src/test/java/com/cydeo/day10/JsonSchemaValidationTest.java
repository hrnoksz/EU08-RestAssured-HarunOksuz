package com.cydeo.day10;

import com.cydeo.utililities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation() {

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                //We use following methods, if .json file under resources directory
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))//!!!!!!!!!!!!!
                .log().all();

    }

    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest() {

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                //what if you have your .json file not under resources following way -->!!!!!!!!!
                //We use following methods, if .json file is not under resources directory
                //Right click on .json file --> click on Copy/Path Reference--> click on Path From Content Root
                //And then past it inside new File parenthesis
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/allSpartansSchema.json")));

    }
    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input(name,gender,phone)
    //verify your post response matching with json schema
}
