package com.wbx.algorithm._04_递归;

/**
 * @describe： 斐波那契数列
 * @Date：2019-10-31 23:47
 * @author：wbx
 * ◼ 斐波那契数列： 1、 1、 2、 3、 5、 8、 13、 21、 34、 ……
 * F(1)=1， F(2)=1, F(n)=F(n-1)+F(n-2)（ n≥3）
 */
public class Fib {


    /**
     * 递归方式实现
     * ◼ 根据递推式 T(n) = T(n − 1) + T(n − 2) + O(1)，可得知时间复杂度： O(2^n)
     * ◼ 空间复杂度： O(n)
     * @param n
     * @return
     */
    public static int fib1(int n) {
        if (n <= 2) {
            return 1;
        }

        return fib1(n - 1) + fib1(n - 2);
    }
}
