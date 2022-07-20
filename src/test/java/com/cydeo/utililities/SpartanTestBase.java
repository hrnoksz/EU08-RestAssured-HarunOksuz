package com.cydeo.utililities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init(){ //MUST BE STATIC!!!!!!!!!!!!!!!!
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://3.83.123.243:8000";

        String dbUrl = "jdbc:oracle:thin:@3.83.123.243:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";

        DBUtils.createConnection(dbUrl, dbUsername, dbPassword);
    }

    @AfterAll //MUST BE STATIC!!!!!!!!!!
    public static void teardown(){

        DBUtils.destroy();
    }
}
