package com.cydeo.day12;

import com.cydeo.utililities.SpartanNewBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OldRestAssuredTest extends SpartanNewBase {

    @Test
    public void getAllSpartan(){

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all()
                .when()
                .get("/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(1))
                .log().all();


    }
    @Test
    public void test2(){
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all()
                .expect()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("id[0]", is(1))
                .logDetail(LogDetail.ALL) //log way using with except()
                .when()
                .get("/spartans");
    }
}
