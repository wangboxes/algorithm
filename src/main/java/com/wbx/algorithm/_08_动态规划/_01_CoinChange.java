package com.wbx.algorithm._08_动态规划;

/**
 * @describe：找零钱
 *  https://leetcode-cn.com/problems/coin-change/
 * @Date：2019/11/14 10:04
 * @author：wbx
 *
 *
 * 动态规划（Dynamic Programming）
 *
 * ◼ 动态规划，简称DP
 * 是求解最优化问题的一种常用策略
 *
 * ◼ 通常的使用套路（一步一步优化）
 * ① 暴力递归（自顶向下，出现了重叠子问题）
 * ② 记忆化搜索（自顶向下）
 * ③ 递推（自底向上）
 *
 *
 * 动态规划的常规步骤 :
 * ◼ 动态规划中的“动态”可以理解为是“会变化的状态”
 * ① 定义状态（状态是原问题、子问题的解）
 * ✓ 比如定义 dp(i) 的含义
 * ② 设置初始状态（边界）
 * ✓ 比如设置 dp(0) 的值
 * ③ 确定状态转移方程
 * ✓ 比如确定 dp(i) 和 dp(i – 1) 的关系
 *
 *
 * 动态规划的一些相关概念
 * ◼ 来自维基百科的解释
 * Dynamic Programming is a method for solving a complex problem by breaking it down into a
 * collection of simpler subproblems, solving each of those subproblems just once, and storing their
 * solutions.
 * ① 将复杂的原问题拆解成若干个简单的子问题
 * ② 每个子问题仅仅解决1次，并保存它们的解
 * ③ 最后推导出原问题的解
 *
 * ◼ 可以用动态规划来解决的问题，通常具备2个特点
 * 最优子结构（最优化原理）：通过求解子问题的最优解，可以获得原问题的最优解
 * 无后效性
 * ✓ 某阶段的状态一旦确定，则此后过程的演变不再受此前各状态及决策的影响（未来与过去无关）
 * ✓ 在推导后面阶段的状态时，只关心前面阶段的具体状态值，不关心这个状态是怎么一步步推导出来的
 *
 */
public class _01_CoinChange {

    public static void main(String[] args) {
        System.out.println(coins1(41));
        System.out.println(coins2(41));
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
     *
     *
     *
     * ◼ 假设有25分、 20分、 5分、 1分的硬币，现要找给客户41分的零钱，如何办到硬币个数最少？
     * 此前用贪心策略得到的并非是最优解（贪心得到的解是 5 枚硬币）
     *
     * ◼ 假设 dp(n) 是凑到 n 分需要的最少硬币个数
     * 如果第 1 次选择了 25 分的硬币，那么 dp(n) = dp(n – 25) + 1
     * 如果第 1 次选择了 20 分的硬币，那么 dp(n) = dp(n – 20) + 1
     * 如果第 1 次选择了 5 分的硬币，那么 dp(n) = dp(n – 5) + 1
     * 如果第 1 次选择了 1 分的硬币，那么 dp(n) = dp(n – 1) + 1
     * 所以 dp(n) = min { dp(n – 25), dp(n – 20), dp(n – 5), dp(n – 1) } + 1
     *
     *
     *
     */
    static int coins1(int n) {
        if (n < 1){
            return Integer.MAX_VALUE;
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
            return Integer.MAX_VALUE;
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
