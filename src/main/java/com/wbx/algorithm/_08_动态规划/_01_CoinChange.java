package com.wbx.algorithm._08_动态规划;

/**
 * @describe：找零钱
 *  https://leetcode-cn.com/problems/coin-change/
 * @Date：2019/11/14 10:04
 * @author：wbx
 */
public class _01_CoinChange {

    public static void main(String[] args) {
        //System.out.println(coins1(41));
        //System.out.println(coins2(41));
        System.out.println(coins3(41));
    }

    /**
     *
     * @param coins 硬币的面额
     * @param amount 总金额
     * @return 凑成总金额所需的最少的硬币个数
     */
    public static int coinChange1(int[] coins, int amount) {


        return -1;
    }


    /**
     * 方式1: 暴力递归（自顶向下的调用，出现了重叠子问题）
     *
     * 类似于斐波那契数列的递归版，会有大量的重复计算，时间复杂度较高
     */
    static int coins1(int n) {
        if (n < 1){
            return -1;
        }
        if (n == 25 || n == 20 || n == 5 || n == 1) {
            return 1;
        }
        int min1 = Math.min(coins1(n - 25), coins1(n - 20));
        int min2 = Math.min(coins1(n - 5), coins1(n - 1));
        return Math.min(min1, min2) + 1;
    }

    /**
     * 方式2: 记忆化搜索（自顶向下的调用）
     */
    static int coins2(int n) {
        if (n < 1){
            return -1;
        }
        int[] dp = new int[n + 1];
        int[] faces = {1, 5, 20, 25};
        for (int face : faces) {
            if (n < face) {
                break;
            }
            dp[face] = 1;
        }
        return coins2(n, dp);
    }

    static int coins2(int n, int[] dp) {
        if (n < 1) {
            return Integer.MAX_VALUE;
        }
        if (dp[n] == 0){
            int min1 = Math.min(coins2(n - 25, dp), coins2(n - 20, dp));
            int min2 = Math.min(coins2(n - 5, dp), coins2(n - 1, dp));
            dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }

    /**
     * 方式3: 递推（自底向上）
     *
     * 时间复杂度、空间复杂度： O(n)
     */
    static int coins3(int n) {
        if (n < 1) {
            return -1;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = dp[i - 1];

            if (i >= 5){
                min = Math.min(dp[i - 5], min);
            }
            if (i >= 20){
                min = Math.min(dp[i - 20], min);
            }
            if (i >= 25){
                min = Math.min(dp[i - 25], min);
            }


            dp[i] = min + 1;
        }
        return dp[n];
    }
}
