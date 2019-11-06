package com.wbx.algorithm._05_回溯;

/**
 * @describe： 优化2 : 把布尔数组用位来表示, 只适用于8皇后及以下
 * @Date：2019/11/6 16:37
 * @author：wbx
 */
public class Queens3 {

    /**
     * 数组索引是行号，数组元素是列号
     */
    int[] queens;
    /**
     * 标记着某一列是否有皇后
     */
    byte cols;
    /**
     * 标记着某一斜线上是否有皇后（左上角 -> 右下角）
     */
    short leftTop;
    /**
     * 标记着某一斜线上是否有皇后（右上角 -> 左下角）
     */
    short rightTop;
    /**
     * 一共有多少种摆法
     */
    int ways;

    public void place8Queens() {
        queens = new int[8];
        place(0);
        System.out.println("8皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第row行开始摆放皇后
     * @param row
     */
    private void place(int row) {
        if (row == 8) {
            ways++;
            show();
            return;
        }

        for (int col = 0; col < 8; col++) {

            //剪枝
            int cv = 1 << col;
            if ((cols & cv) != 0) {
                continue;
            }

            //左上角 -> 右下角的对角线索引(画图观察)： row – col + 7
            int lv = 1 << (row - col + 7);
            if ((leftTop & lv) != 0) {
                continue;
            }

            //右上角 -> 左下角的对角线索引： row + col
            int rv = 1 << (row + col);
            if ((rightTop & rv) != 0) {
                continue;
            }

            queens[row] = col;
            cols |= cv;
            leftTop |= lv;
            rightTop |= rv;
            place(row + 1);

            //注意:在回退后,要恢复现场
            // 01111101 n
            //&11111011  <--- ~00000100
            // 01111001
            cols &= ~cv;
            leftTop &= ~lv;
            rightTop &= ~rv;
        }
    }

    private void show() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
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
