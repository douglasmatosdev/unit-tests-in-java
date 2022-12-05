package com.douglasmatosdev;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOrder {
    public static int counter = 0;

    @Test
    public void start() {
        counter = 1;
    }

    @Test
    public void verify(){
        // Depende que o método start rode antes
        Assert.assertEquals(1, counter);
    }
}
