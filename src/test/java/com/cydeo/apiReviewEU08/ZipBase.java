package com.cydeo.apiReviewEU08;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class ZipBase {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://api.zippopotam.us";
        basePath = "/us";

    }
}
