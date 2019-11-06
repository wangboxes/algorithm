package com.wbx.algorithm._05_回溯;

/**
 * @describe： 优化1: 优化剪枝操作, 去掉for循环, 把剪枝的时间复杂度从O(n) 降到O(1)
 * @Date：2019/11/6 16:28
 * @author：wbx
 */
public class Queens2 {

    /**
     * 数组索引是行号，数组元素是列号
     */
    int[] queens;
    /**
     * 标记着某一列是否有皇后
     */
    boolean[] cols;
    /**
     * 标记着某一斜线上是否有皇后（左上角 -> 右下角）
     */
    boolean[] leftTop;
    /**
     * 标记着某一斜线上是否有皇后（右上角 -> 左下角）
     */
    boolean[] rightTop;
    /**
     * 一共有多少种摆法
     */
    int ways;

    public void placeQueens(int n) {
        if (n < 1) {
            return;
        }
        queens = new int[n];
        cols = new boolean[n];
        //对角线的条数有: n * 2 - 1 条
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];

        place(0);

        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第row行开始摆放皇后
     * @param row
     */
    private void place(int row) {
        //成功的摆法
        if (row == cols.length) {
            ways++;
            show();
            return;
        }

        for (int col = 0; col < cols.length; col++) {

            //剪枝
            if (cols[col]) {
                continue;
            }

            //左上角 -> 右下角的对角线索引(画图观察)： row – col + 7
            int ltIndex = row - col + cols.length - 1;
            if (leftTop[ltIndex]){
                continue;
            }

            //右上角 -> 左下角的对角线索引： row + col
            int rtIndex = row +col;
            if (rightTop[rtIndex]) {
                continue;
            }

            queens[row] = col;
            cols[col] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;

            place(row + 1);

            //注意:在回退后,要恢复现场
            cols[col] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;
        }
    }

    private void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (queens[row] == col) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------");
    }
}
