package com.wbx.tools;

/**
 * @describe：
 * @Date：2019/12/30 16:10
 * @author：wbx
 */
public class A {

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        int result = fun(gas, cost);
        System.out.println(result);
    }

    public static int fun(int[] gas, int[] cost) {
        int startIndex = 0;
        int len = gas.length;
        for (int i = 0; i < len; i++) {
            if (gas[i] >= cost[i]) {
                startIndex = i;

                int total = 0;
                for (int j = startIndex; j < len; j++) {
                    total = total + gas[j] - cost[j];
                    if (total<0){
                        break;
                    }
                }

                if (total < 0) {
                    total = 0;
                    continue;
                }

                for (int j = 0; j < startIndex; j++) {
                    total = total + gas[j] - cost[j];
                    if (total<0){
                        break;
                    }
                }

                if (total < 0) {
                    total = 0;
                    continue;
                }

                return startIndex;

            }

        }

        return -1;

    }
}
