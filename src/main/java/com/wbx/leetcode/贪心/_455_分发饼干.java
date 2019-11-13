package com.wbx.leetcode.贪心;

import java.util.Arrays;

/**
 * @describe： https://leetcode-cn.com/problems/assign-cookies/
 * @Date：2019/11/13 16:49
 * @author：wbx
 */
public class _455_分发饼干 {

    /**
     * 目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * 你可以假设胃口值为正。
     * 一个小朋友最多只能拥有一块饼干。
     * @param stomach 胃口值(正数)
     * @param biscuits 饼干尺寸
     * @return
     *
     * 优先满足胃口小的
     * 执行用时 :64 ms, 在所有 java 提交中击败了8.62%的用户
     * 内存消耗 :39 MB, 在所有 java 提交中击败了96.46%的用户
     */
    public int findContentChildren(int[] stomach, int[] biscuits) {
        Arrays.sort(biscuits);
        Arrays.sort(stomach);

        int count = 0;
        int begin = 0;
         //[1,1]
        for (int i = 0; i < biscuits.length; i++) {

            //[1,2,3]
            for (int j = begin; j < stomach.length; j++) {
                if (biscuits[i]>= stomach[j]) {
                    count ++;
                    begin++;
                    break;
                }
            }

        }


        return count;
    }


    public int findContentChildren2(int[] stomach, int[] biscuits) {
        Arrays.sort(biscuits);
        Arrays.sort(stomach);

        int count = 0;
        int b = 0;
        while (count < stomach.length && b < biscuits.length) {
            if (stomach[count] <= biscuits[b++]) {
                count++;
            }
        }

        return count;
    }
}
