package com.cydeo.day10;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class FormulaOneXmlTest {

    @BeforeAll
    public static void setUp(){
        //http://ergast.com/api/f1/drivers/alonso
        baseURI ="http://ergast.com";
        basePath = "/api/f1";
    }

    @Test
    public void test1(){
        Response response = given()
                                .pathParam("driver", "alonso")
                            .when()
                                .get("/drivers/{driver}")
                            .then().statusCode(200).log().all()
                                    .extract().response();
    }
}
