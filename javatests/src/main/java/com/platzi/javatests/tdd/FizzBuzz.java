package com.platzi.javatests.tdd;

public class FizzBuzz {

    public static String fizzBuzz(int i) {

        boolean dividableByThree = (i % 3) == 0 ? true : false;
        boolean dividableByFive = (i % 5) == 0 ? true : false;

        if (dividableByThree && dividableByFive){
            return "FizzBuzz";
        }

        if (dividableByThree){
            return "Fizz";
        }

        if (dividableByFive){
            return "Buzz";
        }

        return Integer.toString(i);
    }
}
