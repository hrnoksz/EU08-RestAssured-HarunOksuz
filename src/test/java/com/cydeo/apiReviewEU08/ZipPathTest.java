package com.cydeo.apiReviewEU08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class ZipPathTest extends ZipBase {

    @Test
    public void pathTest(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when()
                .get("/{zip}");
        response.prettyPrint();

    }
}
