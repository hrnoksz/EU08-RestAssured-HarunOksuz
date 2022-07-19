package com.cydeo.day05;

import com.cydeo.utililities.DBUtils;
import com.cydeo.utililities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1(){

        //get id,name,gender phone  from database
        //get same information from api
        //compare

        //1. get id,name,gender phone from database and save data inside the map
        String query = "select spartan_id, name, gender, phone \n" +
                "from spartans\n" +
                "where spartan_id = 15";

        Map<String, Object> dbMap = DBUtils.getRowMap(query); //This data is coming from database
        System.out.println(dbMap); //EXPECTED RESULT

        //2.Get information from API
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .extract().response();

        //Deserialization here JSON to JAVA with Jackson objectMapper
        Map<String, Object> apiMap = response.as(Map.class);

        System.out.println(apiMap);

        //3.Compare DATABASE vs API ---> we assume expected result is coming from db
        assertThat(apiMap.get("id").toString(), is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"), is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"), is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(), is(dbMap.get("PHONE").toString()));
    }
}
