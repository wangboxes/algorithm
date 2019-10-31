package com.wbx.algorithm._04_递归;

import org.junit.Assert;
import org.junit.Test;

/**
 * @describe：
 * @Date：2019-10-31 23:13
 * @author：wbx
 */
public class RecursionTest {

    @Test
    public void testSum() {
        int sum1 = Recursion.sum1(100);
        int sum2 = Recursion.sum2(100);
        int sum3 = Recursion.sum3(100);
        System.out.println("sum1 = " + sum1);
        Assert.assertEquals(5050, sum1);
        Assert.assertEquals(5050, sum2);
        Assert.assertEquals(5050, sum3);

    }
}
