package com.wbx.data_structure._09_哈希表;

import java.util.HashMap;

/**
 * @describe：
 * @Date：2019/5/18 18:13
 * @author：wbx
 */
public class TestHash {


    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("one","diyi");
        Integer i = 123;
        Long l = 123L;
        Float f = 10.6F;
        Double d = 10.6;
        String s = "jack";

        System.out.println(i.hashCode());
        System.out.println(l.hashCode());
        System.out.println(f.hashCode());
        System.out.println(d.hashCode());
        System.out.println(s.hashCode());


        System.out.println(Integer.hashCode(123));
        System.out.println(Float.hashCode(10.6F));



    }
}
