package com.wbx.algorithm._01_sort;

import com.wbx.algorithm._01_sort.notComparison._09_RadixSort1;
import com.wbx.tools.Integers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @describe：
 * @Date：2019/9/26 17:53
 * @author：wbx
 */
public class _09_RadixSort1Test {

    @Test
    public void test() {
        Integer[] array = {7,3,5,8,6,7,4,5};
        //Integer[] array = Integers.random(100, 1, 200);
        Sort sort = new _09_RadixSort1();
        sort.sort(array);

        System.out.println("array = " + Arrays.toString(array));
        System.out.println(sort);

        Assert.assertTrue(Integers.isAscOrder(array));
    }
}