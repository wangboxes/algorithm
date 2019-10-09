package com.wbx._01_sort;

import com.wbx._01_sort.notComparison._08_CountingSort1;
import com.wbx._01_sort.notComparison._08_CountingSort2;
import com.wbx.tools.Integers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @describe：
 * @Date：2019/9/26 17:53
 * @author：wbx
 */
public class _08_CountingSort2Test {

    @Test
    public void test() {
        Integer[] array = {7,3,5,8,6,7,4,5};
        //Integer[] array = Integers.random(100, 1, 200);
        Sort sort = new _08_CountingSort2();
        sort.sort(array);

        System.out.println("array = " + Arrays.toString(array));
        System.out.println(sort);

        Assert.assertTrue(Integers.isAscOrder(array));
    }
}