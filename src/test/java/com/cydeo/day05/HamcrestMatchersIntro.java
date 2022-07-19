package com.cydeo.day05;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

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


        //

    }
}
