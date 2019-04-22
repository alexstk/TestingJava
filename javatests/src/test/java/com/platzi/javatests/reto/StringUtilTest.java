package com.platzi.javatests.reto;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void isNotEmpty() {
        assertFalse(StringUtil.isEmpty("alex"));
    }

    @Test
    public void nullString() {
        assertTrue(StringUtil.isEmpty(null));
    }

    @Test
    public void whiteSpace() {
        assertTrue(StringUtil.isEmpty(" "));
    }

}