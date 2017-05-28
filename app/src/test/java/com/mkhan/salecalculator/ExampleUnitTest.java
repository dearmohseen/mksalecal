package com.mkhan.salecalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String finalValue = "23.21";
        String xx =  finalValue.substring(finalValue.indexOf("."));

        if(xx.length() > 3){
            System.out.println("Orig : " + xx + " New : " + xx.substring(0,3));
        } else {
            System.out.println("Orig : " + xx );
        }

        assertEquals(4, 2 + 2);
    }
}