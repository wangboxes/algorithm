package com.wbx._02_union_find;

import com.wbx.tools.Times;
import org.junit.Assert;
import org.junit.Test;

/**
 * @describe：
 * @Date：2019/10/12 11:27
 * @author：wbx
 */
public class UnionFindTest {

    static final int count = 100000;

    @Test
    public void test() {
        testTime(new _01_UnionFind_QF(count));
		testTime(new _02_UnionFind_QU(count));
        testTime(new _03_UnionFind_QU_Size(count));
//        testTime(new UnionFind_QU_R(count));
//        testTime(new UnionFind_QU_R_PC(count));
//        testTime(new UnionFind_QU_R_PS(count));
//        testTime(new UnionFind_QU_R_PH(count));
    }

    private void testTime(UnionFind uf) {
        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);

        uf.union(6, 7);

        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);

        Assert.assertFalse(uf.isSame(2, 7));

        uf.union(4, 6);

        Assert.assertTrue(uf.isSame(2, 7));

        Times.test(uf.getClass().getSimpleName(), () -> {

            for (int i = 0; i < count; i++) {
                uf.union((int)(Math.random() * count), (int)(Math.random() * count));
            }

            for (int i = 0; i < count; i++) {
                uf.isSame((int)(Math.random() * count), (int)(Math.random() * count));
            }
        });
    }
}
