package com.wbx._01_sort;

import com.wbx._01_sort.notComparison._10_BucketSort;
import com.wbx.tools.Integers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @describe：
 * @Date：2019/9/26 17:53
 * @author：wbx
 */
public class _10_BucketSortTest {

    @Test
    public void test() {
        Double[] array = new Double[]{0.34,0.47,0.29,0.84,0.45,0.38,0.35,0.76};
        Sort sort = new _10_BucketSort();
        sort.sort(array);

        System.out.println("array = " + Arrays.toString(array));
        System.out.println(sort);

        Assert.assertTrue(Integers.isAscOrder(array));
    }


}