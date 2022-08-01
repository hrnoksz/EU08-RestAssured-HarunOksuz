package com.cydeo.utililities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {

    @BeforeAll
    public static void init() { //MUST BE STATIC!!!!!!!!!!!!!!!!
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://3.83.123.243";
        port = 7000;
        basePath = "/api";

    }
    @AfterAll
    public static void close(){
        //reset the info we set above, method comes from RestAssured
        reset();
    }

}
