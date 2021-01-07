package com.devxschoolsummer;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class HamcrestMatchersTest {
    @Test
    public void hamcrestMatchers_1(){
        String animal ="Giraffe";
        //MatcherAssert.assertThat(animal,Matchers.equalTo("Elephante"));
        MatcherAssert.assertThat(animal,Matchers.is("Giraffe"));
        MatcherAssert.assertThat(animal,Matchers.equalToIgnoringCase("giraffe"));


    }
    @Test
    public void hamcrestMatchers_2(){
        List<String> animals= Arrays.asList("Elephant","Giraffe","Crocodile");
        MatcherAssert.assertThat(animals,Matchers.hasItem("Crocodile"));
        //MatcherAssert.assertThat(animals,Matchers.hasItem("Elephant", "Lion","Crocodile"));
        MatcherAssert.assertThat(animals, Matchers.anyOf(Matchers.hasItem("Giraffe"),Matchers.hasItem("Lion")));

    }
}
