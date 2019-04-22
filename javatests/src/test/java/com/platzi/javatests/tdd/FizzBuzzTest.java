package com.platzi.javatests.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

public class FizzBuzzTest {

    @Test
    public void isDividableByThree(){
        assertTrue("Fizz".equals(FizzBuzz.fizzBuzz(3)));
    }

    @Test
    public void isDividableByFive(){
        assertTrue("Buzz".equals(FizzBuzz.fizzBuzz(5)));
    }

    @Test
    public void isDividableByThreeAndFive(){
        assertTrue("FizzBuzz".equals( FizzBuzz.fizzBuzz(15)));
    }

    @Test
    public void isNotDividableByThreeOrFive(){
        assertTrue("26".equals( FizzBuzz.fizzBuzz(26)));
    }
}
