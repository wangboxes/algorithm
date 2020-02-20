package com.wbx.algorithm._08_动态规划;

/**
 * @describe： 最长上升子序列（最长递增子序列， Longest Increasing Subsequence， LIS）
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * @Date：2019/11/14 10:04
 * @author：wbx
 */
public class _03_LIS {

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[] {10, 2, 2, 5, 1, 7, 101, 18}));
    }

    /**
     * ◼ 状态转移方程
     * 当 nums[i] > nums[j]
     * ✓ nums[i] 可以接在 nums[j] 后面，形成一个比 dp(j) 更长的上升子序列，长度为 dp(j) + 1
     * ✓ dp(i) = max { dp(i), dp(j) + 1 }
     *
     * 当 nums[i] ≤ nums[j]
     * ✓ nums[i] 不能接在 nums[j] 后面，跳过此次遍历（continue）
     *
     * ◼ 状态的初始值
     * dp(0) = 1
     * 所有的 dp(i) 默认都初始化为 1
     */
    static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        //1.定义状态 :假设 dp(i) 是以 nums[i] 结尾的最长上升子序列的长度
        //2.设置初始状态
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //3.确定状态转移方程(即递推式)
                if (nums[i] <= nums[j]) {
                    continue;
                }
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            max = Math.max(dp[i], max);
        }
        return max;
    }

}
