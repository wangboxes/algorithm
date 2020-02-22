package com.wbx.leetcode.第三季.精选TOP面试题;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe：
 * @Date：2020/2/21 20:02
 * @author：wbx
 */
public class _01_1_两数之和 {


    //最优解(没看)
    public int[] twoSum(int[] nums, int target) {
        int volume = 2048; //100000000000
        int bitMode = volume - 1;//011111111111
        int[] result = new int[volume];
        for (int i = 0; i < nums.length; i++) {
            int c = (target - nums[i]) & bitMode;
            if (result[c] != 0) {
                return new int[]{result[c] - 1, i};
            }
            result[nums[i] & bitMode] = i + 1;
        }
        return null;
    }

    //一遍hash表（题解）
    /**
     * 复杂度分析：
     *
     * 时间复杂度：O(n)，
     * 我们只遍历了包含有 n个元素的列表一次。在表中进行的每次查找只花费 O(1) 的时间。
     *
     * 空间复杂度：O(n)，
     * 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 n 个元素。
     */
    public int[] twoSum2(int[] nums, int target) {
        //key：nums[i]  , value : i
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int dif = target - nums[i];
            if (map.containsKey(dif) && map.get(dif) != i) {
                return new int[]{map.get(dif), i};
            }

            map.put(nums[i], i);

        }


        throw new IllegalArgumentException("No two sum solution");
    }

    //暴力法（自己）
    public int[] twoSum1(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }

        }

        throw new IllegalArgumentException("No two sum solution");


    }


}
