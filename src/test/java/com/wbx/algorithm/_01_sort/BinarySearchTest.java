package com.wbx.algorithm._01_sort;

import com.wbx.tools.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * @describe：
 * @Date：2019/9/27 10:51
 * @author：wbx
 */
public class BinarySearchTest {

    @Test
    public void testIndexOf() {
        int[] array = {2, 4, 8, 8, 8, 12, 14};
        int index = BinarySearch.indexOf(array, 12);
        Assert.assertEquals(5,index);

        //8有重复值,返回哪个index不稳定
        Assert.assertEquals(3, BinarySearch.indexOf(array,8));
        int[] array2 = {2, 4, 8, 8, 8, 12, 14, 15};
        Assert.assertEquals(4, BinarySearch.indexOf(array2,8));


    }
}
