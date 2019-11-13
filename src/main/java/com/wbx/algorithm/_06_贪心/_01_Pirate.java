package com.wbx.algorithm._06_贪心;

import java.util.Arrays;

/**
 * @describe： 最优装载问题（加勒比海盗）
 * @Date：2019/11/13 10:56
 * @author：wbx ◼ 贪心策略，也称为贪婪策略
 * 每一步都采取当前状态下最优的选择（局部最优解），从而 "希望" 推导出全局最优解
 * <p>
 * ◼ 贪心的应用
 * 哈夫曼树
 * 最小生成树算法： Prim、 Kruskal
 * 最短路径算法： Dijkstra
 *
 * ◼ 贪心策略并不一定能得到全局最优解
 * 因为一般没有测试所有可能的解，容易过早做决定，所以没法达到最佳解
 * 贪图眼前局部的利益最大化，看不到长远未来，走一步看一步
 * ◼ 优点：简单、高效、不需要穷举所有可能，通常作为其他算法的辅助算法来使用
 * ◼ 缺点：鼠目寸光，不从整体上考虑其他可能，每次采取局部最优解，不会再回溯，因此很少情况会得到最优解
 */
public class _01_Pirate {

    /**
     * 海盗船的载重量为 W，每件古董的重量为 i，海盗们该如何把尽可能多数量的古董装上海盗船？
     * 比如 W 为 30， i 分别为 3、 5、 4、 10、 7、 14、 2、 11
     * ◼ 贪心策略：每一次都优先选择重量最小的古董
     * ① 选择重量为 2 的古董，剩重量 28
     * ② 选择重量为 3 的古董，剩重量 25
     * ③ 选择重量为 4 的古董，剩重量 21
     * ④ 选择重量为 5 的古董，剩重量 16
     * ⑤ 选择重量为 7 的古董，剩重量 9
     * 最多能装载 5 个古董
     */
    public static void main(String[] args) {
        int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};
        Arrays.sort(weights);
        int capacity = 30;
        int count = 0;

        for (int i = 0; i < weights.length; i++) {
            if (capacity - weights[i] >= 0) {
                count++;
                capacity -= weights[i];
                System.out.println(weights[i]);
            }

        }
        System.out.printf("一共选了%s件古董", count);


    }

}
