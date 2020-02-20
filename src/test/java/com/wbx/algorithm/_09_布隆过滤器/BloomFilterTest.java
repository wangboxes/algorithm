package com.wbx.algorithm._09_布隆过滤器;

import org.junit.Test;

/**
 * @describe：
 * @Date：2019/11/6 16:21
 * @author：wbx
 */
public class BloomFilterTest {

    /**
     * 测试错误率
     */
    @Test
    public void test1() {
        BloomFilter<Integer> bf = new BloomFilter<>(1_00_0000, 0.01);
        for (int i = 1; i <= 1_00_0000; i++) {
            bf.put(i);
        }

        int count = 0;
        for (int i = 1_00_0001; i <= 2_00_0000; i++) {
            if (bf.mightContain(i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * 模拟爬虫的url去重
     */
    @Test
    public void test2() {
        // 数组
        String[] urls = {};
        BloomFilter<String> bf = new BloomFilter<>(10_0000_0000, 0.01);
        for (String url : urls) {
            if (bf.mightContain(url)) continue;
            // 爬这个url
            // ......

            // 放进BloomFilter中
            bf.put(url);
        }
    }

    /**
     * 模拟爬虫的url去重，test2和test3效果一样
     */
    @Test
    public void test3() {
        // 数组
        String[] urls = {};
        BloomFilter<String> bf = new BloomFilter<>(10_0000_0000, 0.01);
        for (String url : urls) {
            if (bf.put(url) == false) {
                continue;
            }
            // 爬这个url
            // ......
        }
    }

}
