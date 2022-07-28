package com.cydeo.day10;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class FormulaOneXmlTest {

    @BeforeAll
    public static void setUp(){
        baseURI ="http://ergast.com";


    }
}
