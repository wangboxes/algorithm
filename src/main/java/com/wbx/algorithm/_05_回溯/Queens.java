package com.wbx.algorithm._05_回溯;

/**
 * @describe：n皇后
 * @Date：2019/11/6 15:48
 * @author：wbx
 *
 * 回溯（Back Tracking）
 * ◼ 回溯可以理解为：通过选择不同的岔路口来通往目的地（找到想要的结果）
 * 每一步都选择一条路出发，能进则进，不能进则退回上一步（回溯），换一条路再试
 * ◼ 树、图的深度优先搜索（DFS）就是典型的回溯应用
 * ◼ 不难看出来，回溯很适合使用递归
 *
 *
 * 练习 – 八皇后问题（Eight Queens）
 * ◼ 八皇后问题是一个古老而著名的问题
 * 在8x8格的国际象棋上摆放八个皇后，使其不能互相攻击：任意两个皇后都不能处于同一行、同一列、同一斜线上
 * 请问有多少种摆法？
 *  ◼ leetcode_51_N皇后： https://leetcode-cn.com/problems/n-queens/
 * ◼ leetcode_52_N皇后 II： https://leetcode-cn.com/problems/n-queens-ii/
 *
 *
 * 八皇后问题的解决思路
 *
 * ◼ 思路一：暴力出奇迹
 * 从 64 个格子中选出任意 8 个格子摆放皇后，检查每一种摆法的可行性
 * 一共 C8 64 种摆法（大概是 4.4 ∗ 10^9 种摆法）
 *
 * ◼ 思路二：根据题意减小暴力程度
 * 很显然，每一行只能放一个皇后，所以共有 8^8 种摆法（16777216 种），检查每一种摆法的可行性
 *
 * ◼ 思路三：回溯法
 * 回溯 + 剪枝（Pruning）
 */
public class Queens {

    /**
     * 数组索引是行号，数组元素是列号
     */
    int[] cols;

    /**
     * 一共有多少种摆法
     */
    int ways;


    /**
     * 摆放皇后
     * @param n  n皇后
     *
     */
    public void placeQueens(int n) {
        if (n < 1) {
            return;
        }
        cols = new int[n];
        place(0);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }


    /**
     * 从第row行开始摆放皇后
     * @param row
     */
    private void place(int row) {
        //成功的摆法
        if (row == cols.length) {
            ways++;
            show();
            return;
        }

        for (int col = 0; col < cols.length; col++) {
            //剪枝
            if (isValid(row, col)) {
                // 在第row行第col列摆放皇后
                cols[row] = col;
                place(row + 1);
            }
        }
    }


    /**
     * 判断第row行第col列是否可以摆放皇后
     */
    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 第col列已经有皇后---------(同一列不能有皇后)
            if (cols[i] == col) {
                //System.out.println("[" + row + "][" + col + "]=false");
                return false;
            }

            // 第i行的皇后跟第row行第col列格子处在同一斜线上(数学知识:45°角,斜率 = 1)--------(同一斜线(左,右斜线)上不能有皇后)
            if (row - i == Math.abs(col - cols[i])) {
                //System.out.println("[" + row + "][" + col + "]=false");
                return false;
            }
        }
        //System.out.println("[" + row + "][" + col + "]=true");
        return true;
    }

    private void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------");
    }

}
