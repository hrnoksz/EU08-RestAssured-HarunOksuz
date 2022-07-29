package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    //beforeClass is testNg version of beforeAll, same logic
    @BeforeAll
    public static void init(){
        System.out.println("Before all is running");
    }

    //beforeMethod is testNg version of beforeEach, same logic
    @BeforeEach
    public void initEach(){

        System.out.println("\tBefore each is running");
    }

    //afterMethod is testNg version of afterEach, same logic
    @AfterEach
    public void closeEach(){
        System.out.println("\tAfter each is running ");
    }

    @Test
    public void test1(){

        System.out.println("Test1 is running");
    }

    @Disabled //Means ignore the test--test2 will not be executed
    @Test
    public void test2(){

        System.out.println("Test2 is running");
    }

    @AfterAll
    public static void close(){
        System.out.println("After all is running");
    }

}
/*
Before all is running

	Before each is running
Test1 is running
	After each is running

	Before each is running
Test2 is running
	After each is running

After all is running
 */
