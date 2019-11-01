package com.wbx.algorithm._04_递归;

import org.junit.Assert;
import org.junit.Test;

/**
 * @describe：
 * @Date：2019-10-31 23:49
 * @author：wbx
 */
public class _02_FibTest {

    @Test
    public void testFib() {
        int fib = _02_Fib.fib(9);
        System.out.println("fib = " + fib);

        Assert.assertEquals(34, _02_Fib.fib(9));

        Assert.assertEquals(34, _02_Fib.fib1(9));
        Assert.assertEquals(34, _02_Fib.fib2(9));
        Assert.assertEquals(34, _02_Fib.fib3(9));
        Assert.assertEquals(34, _02_Fib.fib3_1(9));
        Assert.assertEquals(34, _02_Fib.fib3_2(9));
        Assert.assertEquals(34, _02_Fib.fib4(9));
    }
}
