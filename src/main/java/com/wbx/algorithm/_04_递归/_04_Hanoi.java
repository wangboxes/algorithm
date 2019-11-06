package com.wbx.algorithm._04_递归;

/**
 * @describe：汉诺塔（Hanoi）
 * 1.每次只能移动1个盘子
 * 2.大盘子只能放在小盘子下面
 *
 * @Date：2019/11/1 11:51
 * @author：wbx
 *
 * 汉诺塔 – 思路
 * ◼ 其实分 2 种情况讨论即可
 * 当 n == 1时，直接将盘子从 A 移动到 C
 * 当 n > 1时，可以拆分成3大步骤
 * ① 将 n – 1 个盘子从 A 移动到 B
 * ② 将编号为 n 的盘子从 A 移动到 C
 * ③ 将 n – 1 个盘子从 B 移动到 C
 * ✓ 步骤 ① ③ 明显是个递归调用
 *
 */
public class _04_Hanoi {

    /**
     * 将 n 个碟子从 p1 挪动到 p3 (pillar 柱子)
     *
     * @param p2 中间的柱子
     */
    void hanoi(int n, String p1, String p2, String p3) {
        if (n == 1) {
            move(n, p1, p3);
            return;
        }
        hanoi(n - 1, p1, p3, p2);
        move(n, p1, p3);
        hanoi(n - 1, p2, p1, p3);

    }


    /**
     * 将 no 号盘子从 from 移动到 to
     *
     * @param no
     * @param from
     * @param to
     */
    void move(int no, String from, String to) {
        System.out.println("将" + no + "号盘子从" + from + "移动到" + to);
    }


    // T(n) = 2 * [2 * T(n - 2) + O(1)] + O(1)
    // T(n) = 2^2 * [2 * T(n - 3) + O(1)] + 2 * O(1) + O(1)
    // T(n) = 2^3 * T(n - 3) + (2^2 + 2^1 + 2^0) * O(1)
    // T(n) = 2^(n - 1) * O(1) + (2^(n-2) + ... + 2^2 + 2^1 + 2^0) * O(1)
    // T(n) = [2^(n - 1)+ 2^(n-2) + ... + 2^2 + 2^1 + 2^0] * O(1)
    // T(n) = (2^n - 1) * O(1)
}
