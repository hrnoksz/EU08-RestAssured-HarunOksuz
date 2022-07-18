package com.cydeo.utililities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://3.83.123.243:1000/ords/hr";
    }
}
