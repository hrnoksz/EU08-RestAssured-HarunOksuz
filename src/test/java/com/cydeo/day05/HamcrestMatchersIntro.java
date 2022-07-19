package com.cydeo.day05;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {
    @DisplayName("Assertion with number")
    @Test
    public void simpleTest1(){

        //Hamcrest is assertion library which is provided RestAssured Library
        //MatcherAssert.assertThat()
        //We import MatcherAssert to not write it again and again
        //matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        assertThat(5+5, is(10));
        assertThat(5+5, equalTo(10));

        //below examples is() method is accepting another matchers equal to make it readable
        assertThat(5+5, is(equalTo(10)));

        assertThat(5+5, not(9));
        assertThat(5+5, is(not(9)));
        assertThat(5+5, is(not(equalTo(9))));

        //number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5, is(greaterThan(9)));

    }
    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest(){

        String text = "EU8 is learning Hamcrest";
        //checking for equality is same as numbers
        //we can use one of the following methods
        assertThat(text, is("EU8 is learning Hamcrest"));
        assertThat(text, equalTo("EU8 is learning Hamcrest"));
        assertThat(text, is(equalTo("EU8 is learning Hamcrest")));

        //check if this text starts with EU8
        assertThat(text, startsWith("EU8"));

        //now do it in case-insensitive manner
        assertThat(text, startsWithIgnoringCase("eu8"));

        //endsWith
        assertThat(text, endsWith("rest"));

        //check if text contains String learning
        assertThat(text, containsString("learning"));

        assertThat(text, containsStringIgnoringCase("LEARNING"));

        String str = " ";
        //check if above str is blank
        assertThat(str, blankString());

        //check if trimmed str is empty string
        assertThat(str.trim(), emptyString());

    }
    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection(){

        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,32);

        //check size of the list
        assertThat(listOfNumbers, hasSize(10));

        //check if this list hasItem 77
        assertThat(listOfNumbers, hasItem(77));

        //check if this list hasItems 77, 54, 23
        assertThat(listOfNumbers, hasItems(77, 54, 23));

        //check if all numbers greater than 0 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        assertThat(listOfNumbers, everyItem(greaterThan(0)));


    }

}
