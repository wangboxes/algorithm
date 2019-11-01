package com.wbx.algorithm._04_递归;

/**
 * @describe： 斐波那契数列
 * @Date：2019-10-31 23:47
 * @author：wbx
 * ◼ 斐波那契数列： 1、 1、 2、 3、 5、 8、 13、 21、 34、 ……
 * F(1)=1， F(2)=1, F(n)=F(n-1)+F(n-2)（ n≥3）
 */
public class _02_Fib {


    /**
     * 递归方式实现
     * ◼ 根据递推式 T(n) = T(n − 1) + T(n − 2) + O(1)，可得知时间复杂度： O(2^n)
     * ◼ 空间复杂度： O(n)
     *
     * 缺点:
     * ◼ 出现了特别多的重复计算
     * ◼ 这是一种“自顶向下”的调用过程
     */
    public static int fib(int n) {
        if (n <= 2) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    /**
     * fib优化1: 用数组存放计算过的结果，避免重复计算
     *
     *
     * 时间复杂度(优化)： O(n)，空间复杂度： O(n)
     */
    public static int fib1(int n) {
        if (n <= 2) {
            return 1;
        }

        int[] array = new int[n + 1];
        array[1] = array[2] = 1;

        return fib1(array, n);

    }

    private static int fib1(int[] array, int n) {

        if (array[n] == 0) {
            array[n] = fib1(array, n - 1) + fib1(array, n - 2);
        }

        return array[n];
    }


    /**
     * fib优化2: 去除递归调用
     *
     * ◼ 时间复杂度： O(n)，空间复杂度： O(n)
     * ◼ 这是一种“自底向上”的计算过程
     */
    public static int fib2(int n) {
        if (n <= 2) {
            return 1;
        }

        int[] array = new int[n + 1];
        array[1] = array[2] = 1;

        //从3开始遍历
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }

        return array[n];
    }

    /**
     * fib优化3: 由于每次运算只需要用到数组中的 2 个元素，所以可以使用滚动数组来优化
     * 时间复杂度： O(n)，空间复杂度(优化)： O(1)
     */
    public static int fib3(int n) {
        if (n <= 2) {
            return 1;
        }
        //只申请了两个元素的数组
        int[] array = new int[2];
        array[0] = array[1] = 1;

        //从3开始遍历
        for (int i = 3; i <= n; i++) {
            array[i % 2] = array[(i - 1) % 2] + array[(i - 2) % 2];
        }

        return array[n % 2];
    }

    /**
     * //TODO learn
     * fib优化3-1: 乘、除、模运算效率较低，建议用其他方式取代( i%2 --> i&1)
     * 时间复杂度： O(n)，空间复杂度(优化)： O(1)
     *
     * 4 % 2 = 0  0b100 & 0b001 = 0
     * 3 % 2 = 1  0b011 & 0b001 = 1
     * 5 % 2 = 1  0b101 & 0b001 = 1
     * 6 % 2 = 0  0b110 & 0b001 = 0
     */
    public static int fib3_1(int n) {
        if (n <= 2) {
            return 1;
        }
        //只申请了两个元素的数组
        int[] array = new int[2];
        array[0] = array[1] = 1;

        //从3开始遍历
        for (int i = 3; i <= n; i++) {
            //优化: 用位运算替代%
            array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
        }

        return array[n & 1];
    }


    /**
     * 用局部变量代替数组
     */
    public static int fib3_2(int n) {
        if (n <= 2) {
            return 1;
        }
        int first = 1;
        int second = 1;

        //从3开始遍历
        for (int i = 3; i <= n; i++) {
            //second保存最后的结果
            second = first + second;
            //first等于上一次的second
            first = second - first;

        }

        return second;
    }

    /**
     * 斐波那契数列有个线性代数解法：特征方程
     *
     * 时间复杂度、空间复杂度取决于 pow 函数（至少可以低至O(logn) ）
     */
    public static int fib4(int n) {
        double c = Math.sqrt(5);
        return (int) ( (Math.pow((1 + c) / 2, n)  -  Math.pow((1 - c) / 2, n) ) / c);
    }



}
