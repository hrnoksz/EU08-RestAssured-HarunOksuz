package com.cydeo.apiReviewEU08;

import com.cydeo.apiReviewEU08.pojo.PostCode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ZipTestPojo extends ZipBase{

    @Test
    public void test(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when()
                .get("/{zip}");

        //De_Serialize to our Custom we created
        PostCode postCode = response.body().as(PostCode.class);
        System.out.println("postCode.getCountry() = " + postCode.getCountry());

        //verify state is Virginia!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //postCode.getPlaces()-->return List of Places, we need to use index
        //postCode.getPlaces().get(0)--> returns me Place Object
        System.out.println("postCode.getPlaces().get(0).getState() = " + postCode.getPlaces().get(0).getState());
    }

}
