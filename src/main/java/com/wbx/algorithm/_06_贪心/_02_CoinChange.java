package com.wbx.algorithm._06_贪心;

import java.util.Arrays;

/**
 * @describe： 零钱兑换
 * @Date：2019/11/13 14:57
 * @author：wbx
 *
 * ◼ 假设有 25 分、 10 分、 5 分、 1 分的硬币，现要找给客户 41 分的零钱，如何办到硬币个数最少？
 * ◼ 贪心策略：每一次都优先选择面值最大的硬币
 * ① 选择 25 分的硬币，剩 16 分
 * ② 选择 10 分的硬币，剩 6 分
 * ③ 选择 5 分的硬币，剩 1 分
 * ④ 选择 1 分的硬币
 * 最终的解是共 4 枚硬币
 * ✓ 25 分、 10 分、 5 分、 1 分硬币各一枚
 *
 *
 *
 */
public class _02_CoinChange {

    public static void main(String[] args) {
        coinChange(new int[]{25, 5, 10, 1},41);
        System.out.println("---------------");
        //实际上本题的最优解是： 2 枚 20 分、 1 枚 1 分的硬币，共 3 枚硬币
        coinChange(new int[] {25, 20, 5, 1}, 41);
    }

    static void coinChange(int[] faces, int money) {
        Arrays.sort(faces);
        int coins = 0;

        for (int i = faces.length - 1; i >= 0; i--) {
            while (money >= faces[i]) {
                money -= faces[i];
                coins++;
                System.out.println("面值"+faces[i]);
            }
        }

        System.out.printf("一共选了%d个硬币", coins);


    }
}
