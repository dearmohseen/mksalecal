package com.mkhan.salecalculator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Double initialPriceDbl = 39.0;
        Double otherPrcntDbl = 33.0;
        Double finalValue = initialPriceDbl - (initialPriceDbl/100)*otherPrcntDbl;

        BigDecimal bd1 = new BigDecimal(initialPriceDbl);
        BigDecimal bd2 = new BigDecimal(otherPrcntDbl);

        Double savings = initialPriceDbl - finalValue;
        System.out.println(" finalValue = " + finalValue + " savings = " + savings + " --- " + bd1.subtract(bd1.divide(new BigDecimal(100)).multiply(bd2))) ;

        assertEquals(4, 2 + 2);
    }
}