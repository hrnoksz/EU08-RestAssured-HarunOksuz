package com.cydeo.day08;

import com.cydeo.utililities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanWithAuthTest extends SpartanAuthTestBase {
    @DisplayName("GET /api/spartans as a public user(guest) expect 401")
    @Test
    public void test1(){

        try{
            given().accept(ContentType.JSON)
                    .when().
                    get("/api/spartans")
                    .then().statusCode(401)
                    .log().all();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdmin(){

        //how to pass admin admin as username and password?
        given()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .log().all();


    }
}
