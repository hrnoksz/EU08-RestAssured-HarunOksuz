package com.cydeo.day05;

import com.cydeo.utililities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class JSONtoJAVATest extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){
        /*
        {
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106
        }
         */
        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map
        Map<String, Object> jsonMap = response.as(Map.class); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        System.out.println(jsonMap.toString()); //{id=15, name=Meta, gender=Female, phone=1938695106}

        //after we got the map, we can use hamcrest
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName, is("Meta"));
        //or junit assertions to do assertion
        assertEquals("Meta", actualName);
    }

    @DisplayName("GET all spartans to Java data structure")
    @Test
    public void getAllSpartan(){

        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        //we need to convert json to java which is deserialize

        List<Map<String, Object>> jsonList = response.as(List.class); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        System.out.println(jsonList.get(1).get("name"));

        Map<String, Object> spartan3 = jsonList.get(2); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println(spartan3);
    }
}
