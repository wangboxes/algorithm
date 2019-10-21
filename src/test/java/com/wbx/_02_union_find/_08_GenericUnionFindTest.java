package com.wbx._02_union_find;

import org.junit.Assert;
import org.junit.Test;

/**
 * @describe：
 * @Date：2019/10/21 10:42
 * @author：wbx
 */
public class _08_GenericUnionFindTest {

    @Test
    public void name() {
        _08_GenericUnionFind<Student> uf = new _08_GenericUnionFind<>();
		Student stu1 = new Student(1, "jack");
		Student stu2 = new Student(2, "rose");
		Student stu3 = new Student(3, "jack");
		Student stu4 = new Student(4, "rose");
		uf.makeSet(stu1);
		uf.makeSet(stu2);
		uf.makeSet(stu3);
		uf.makeSet(stu4);

		uf.union(stu1, stu2);
		Assert.assertTrue(uf.isSame(stu1, stu2));

		uf.union(stu3, stu4);
		Assert.assertTrue(uf.isSame(stu3, stu4));

        Assert.assertFalse(uf.isSame(stu2, stu3));
        Assert.assertFalse(uf.isSame(stu1, stu4));

		uf.union(stu1, stu4);

        Assert.assertTrue(uf.isSame(stu1, stu3));
    }

    private static class Student {
        private int age;
        private String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}
