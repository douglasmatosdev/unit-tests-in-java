package com.douglasmatosdev.services;

import com.douglasmatosdev.exceptions.NotDivideForZero;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void sum() {
        // scenario
        int a = 5;
        int b = 3;

        // action
        int result = calculator.sum(a, b);

        // verification
        Assert.assertEquals(8, result);
    }

    @Test
    public void subtract() {
        // scenario
        int a = 5;
        int b = 3;

        // action
        int result = calculator.subtract(a, b);

        // verification
        Assert.assertEquals(2, result);
    }

    @Test
    public void divide() throws NotDivideForZero {
        // scenario
        int a = 6;
        int b = 3;

        // action
        int result = calculator.divide(a, b);

        // verification
        Assert.assertEquals(2, result);
    }

    @Test(expected = NotDivideForZero.class)
    public void divideForZero() throws NotDivideForZero {
        // scenario
        int a = 10;
        int b = 0;

        // action
        int result = calculator.divide(a, b);

        // verification
    }
}
