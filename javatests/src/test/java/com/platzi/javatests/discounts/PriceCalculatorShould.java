package com.platzi.javatests.discounts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PriceCalculatorShould {

    @Before
    public void setup(){
        PriceCalculator calculator = new PriceCalculator();

    }

    @Test
    public void totalZeroWhenThereAreNoPrices(){
        assertThat(calculator.getTotal(), is(0.0));
    }

    @Test
    public void totalIsTheSumOfPrices(){
        calculator.addPrice(10.2);
        calculator.addPrice(15.5);

        assertThat(calculator.getTotal(), is(25.7));
    }

    @Test
    public void applyDiscountToPrices(){
        PriceCalculator calculator = new PriceCalculator();

        calculator.addPrice(12.5);
        calculator.addPrice(17.5);

        calculator.setDiscount(25.0);

        assertThat(calculator.getTotal(), is(22.5));
    }


}
