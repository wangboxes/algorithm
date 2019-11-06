package com.wbx.algorithm._05_回溯;

import org.junit.Test;

/**
 * @describe：
 * @Date：2019/11/6 16:21
 * @author：wbx
 */
public class QueensTest {

    @Test
    public void testQueens() {
        new Queens().placeQueens(8);
    }

    @Test
    public void testQueens2() {
        System.out.println("优化1 :");
        new Queens2().placeQueens(8);
    }

    @Test
    public void testQueens3() {
        System.out.println("优化2 :");
        new Queens3().place8Queens();
    }
}
