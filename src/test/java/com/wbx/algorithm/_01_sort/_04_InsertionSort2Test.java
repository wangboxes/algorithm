package com.wbx.algorithm._01_sort;

import com.wbx.algorithm._01_sort.comparison._04_InsertionSort2;
import com.wbx.tools.Integers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @describe：
 * @Date：2019/9/26 17:53
 * @author：wbx
 */
public class _04_InsertionSort2Test {

    @Test
    public void test() {
        //Integer[] array = {1, 5, 0, 9, 7, 8};
        Integer[] array = Integers.random(100, 1, 200);
        Sort sort = new _04_InsertionSort2();
        sort.sort(array);
        System.out.println("array = " + Arrays.toString(array));
        System.out.println(sort);

        Assert.assertTrue(Integers.isAscOrder(array));
    }
}