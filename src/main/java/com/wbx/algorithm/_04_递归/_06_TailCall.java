package com.wbx.algorithm._04_递归;



/**
 * @describe：尾调用（Tail Call） / 尾递归（Tail Recursion）
 * @Date：2019/11/6 14:54
 * @author：wbx
 *
 * ◼ 尾调用：一个函数的最后一个动作是调用函数
 * 如果最后一个动作是调用自身，称为尾递归（Tail Recursion），是尾调用的特殊情况
 * ◼ 一些编译器能对尾调用进行优化(重复利用栈帧的空间)，以达到节省栈空间的目的
 *
 * (注:java没有对尾调用进行优化(尾调用涉及到不同函数栈帧不同,需要进行栈帧的伸缩,比较难实现),但对尾递归有优化(尾递归是对自身函数的调用,栈帧可以认为是一样的,不需要进行栈帧的伸缩,比较好实现))
 *
 * ◼ 尾调用优化也叫做尾调用消除（Tail Call Elimination）
 * 如果当前栈帧上的局部变量等内容都不需要用了，当前栈帧经过适当的改变后可以直接当作被尾调用的函数的栈帧
 * 使用，然后程序可以 jump 到被尾调用的函数代码 (如果是开辟栈帧,用的汇编指令是:call)
 * 生成栈帧改变代码与 jump 的过程称作尾调用消除或尾调用优化
 * 尾调用优化让位于尾位置的函数调用跟 goto 语句性能一样高
 *
 * ◼ 消除尾递归里的尾调用比消除一般的尾调用容易很多
 * 比如Java虚拟机（JVM）会消除尾递归里的尾调用，但不会消除一般的尾调用（因为改变不了栈帧）
 * 因此尾递归优化相对比较普遍，平时的递归代码可以考虑尽量使用尾递归的形式
 */
public class _06_TailCall {

    //test1()需要的栈帧比test2()大
    public void test1(){
        int a = 10;
        int b = a + 20;

        //尾调用
        test2(b);
    }

    private void test2(int b) {
        if (b < 0) {
            return;
        }
        //尾递归
        test2(b - 1);
    }

    /**
     * 尾递归示例1 – 阶乘
     *
     * 求n的阶乘:1 * 2 * 3 * 4 * ... * (n - 1) * n
     *
     * @param n
     * @return
     */
    public int factorial(int n) {
        if (n <= 1) {
            return n;
        }
        //◼注意:下面代码不是尾调用(尾递归), 因为它最后1个动作是乘法
        return n * factorial(n - 1);
    }


    /**
     * 转成尾递归
     */
    static int factorial2(int n) {
        return factorial2(n, 1);
    }

    /**
     * result 从大到小累乘的结果
     */
    static int factorial2(int n, int result) {
        if (n <= 1) {
            return result;
        }
        //转成尾递归思路: 第一个参数: 控制递归结束  第二个参数: 把结果传递过去
        return factorial2(n - 1, result * n);
    }


    /**
     * 尾递归示例2 – 斐波那契数列
     */
    public static int fib(int n) {
        if (n <= 2) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }


    public static int fib2(int n) {
        return fib2(n, 1, 1);
    }

    /**
     * 转成尾递归
     */
    private static int fib2(int n, int first, int second) {
        if (n <= 1) {
            return first;
        }
        return fib2(n - 1, second, first + second);

    }


}
