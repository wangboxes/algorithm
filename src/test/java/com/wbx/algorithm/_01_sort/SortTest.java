package com.wbx.algorithm._01_sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @describe：
 * @Date：2019/9/24 17:36
 * @author：wbx
 */
public class SortTest {


    int[] array = {1, 5, 0, 9, 7, 8};

    /**
     * 冒泡排序
     */
    @Test
    public void bubbleSort1() {


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


    /**
     * 插入排序
     */
    @Test
    public void InsertionSort1() {

        for (int begin = 1; begin < array.length; begin++) {

            for (int end = begin; end > 0; end--) {

                if (array[end] < array[end - 1]) {
                    int temp = array[end];
                    array[end] = array[end - 1];
                    array[end - 1] = temp;
                }
            }

        }

        System.out.println("array = " + Arrays.toString(array));

    }


    static void selectionSort(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (array[maxIndex] <= array[begin]) {
                    maxIndex = begin;
                }
            }
            int tmp = array[maxIndex];
            array[maxIndex] = array[end];
            array[end] = tmp;
        }

        // 8 10 9 10
    }

    static void bubbleSort1(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int tmp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = tmp;
                }
            }
        }
    }

    static void bubbleSort2(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int tmp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = tmp;
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }

    static void bubbleSort3(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            // sortedIndex的初始值在数组完全有序的时候有用
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int tmp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = tmp;
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }


}
