package com.wbx.algorithm._01_sort;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @describe： 执行所有排序相关的测试用例
 * @Date：2019-09-26 23:58
 * @author：wbx
 */
@RunWith(Suite.class)
@SuiteClasses({_04_InsertionSort1Test.class,
        _04_InsertionSort2Test.class,
        SortPerformanceTest.class})
public class SortAllTests {
}
