package com.platzi.javatests.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void repeatStringOnce(){
        Assert.assertEquals("hola", StringUtil.repeat("hola", 1));
    }

    @Test
    public void repeatStringMultiple(){
        Assert.assertEquals("holaholahola", StringUtil.repeat("hola", 3));
    }

    @Test
    public void repeatStringZeroTimes(){
        Assert.assertEquals("", StringUtil.repeat("hola", 0));
    }

    // Test with expected exception
    @Test(expected = IllegalArgumentException.class)
    public void repeatStringNegativeTimes(){
        Assert.assertEquals("hola", StringUtil.repeat("hola", -1));
    }
}
