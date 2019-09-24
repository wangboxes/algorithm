package com.wbx._01_sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @describe：
 * @Date：2019/9/24 17:36
 * @author：wbx
 */
public class SortTest {

    @Test
    public void bubbleSort1() {
        int[] array = {1, 5, 0, 9, 7, 8};

        for (int end = array.length; end > 1; end--) {

            for (int begin = 1; begin < end; begin++) {

                if (array[begin] < array[begin - 1]) {
                    int temp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = temp;
                }
            }
        }

        System.out.println("array = " + Arrays.toString(array));


    }
}
