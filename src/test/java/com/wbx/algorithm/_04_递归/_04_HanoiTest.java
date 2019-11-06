package com.wbx.algorithm._04_递归;

import org.junit.Test;

/**
 * @describe：
 * @Date：2019/11/6 12:00
 * @author：wbx
 */
public class _04_HanoiTest {

    @Test
    public void testHanoi() {
        new _04_Hanoi().hanoi(3, "A", "B", "C");
    }
}
