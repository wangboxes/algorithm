package com.wbx.algorithm._08_动态规划;

/**
 * @describe：最大连续子序列和 https://leetcode-cn.com/problems/maximum-subarray/
 * @Date：2019/11/14 10:04
 * @author：wbx
 */
public class _02_MaxSubArray {

    public static void main(String[] args) {
        //System.out.println(maxSubArray1(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    /**
     * ◼ 状态转移方程
     * 如果 dp(i – 1) ≤ 0，那么 dp(i) = nums[i]
     * 如果 dp(i – 1) > 0，那么 dp(i) = dp(i – 1) + nums[i]
     *
     * ◼ 初始状态
     * dp(0) 的值是 nums[0]
     *
     * ◼ 最终的解
     * 最大连续子序列和是所有 dp(i) 中的最大值 max { dp(i) }， i ∈ [0, nums.length)
     */
    static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        //1.定义状态 :假设 dp(i) 是以 nums[i] 结尾的最大连续子序列和（nums是整个序列）
        //2.设置初始状态
        int max = dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //3.确定状态转移方程(即递推式)
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;

    }

    static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //1.定义状态 :假设 dp(i) 是以 nums[i] 结尾的最大连续子序列和（nums是整个序列）
        //2.设置初始状态
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            //3.确定状态转移方程(即递推式)
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp = dp + nums[i];
            }
            max = Math.max(dp, max);
        }
        return max;

    }
}
