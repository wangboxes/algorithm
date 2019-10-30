package com.wbx.algorithm._01_sort.notComparison;

/**
 * @describe： 计数排序 – 对自定义对象进行排序
 * @Date：2019/10/12 16:04
 * @author：wbx 如果自定义对象可以提供用以排序的整数类型，依然可以使用计数排序
 */
public class _08_CountingSort3 {

    public static void main(String[] args) {
        Person[] persons = new Person[]{
                new Person(20, "A"),
                new Person(-13, "B"),
                new Person(17, "C"),
                new Person(12, "D"),
                new Person(-13, "E"),
                new Person(20, "F")
        };

        // 找出最值
        int max = persons[0].age;
        int min = persons[0].age;
        for (int i = 1; i < persons.length; i++) {
            if (persons[i].age > max) {
                max = persons[i].age;
            }
            if (persons[i].age < min) {
                min = persons[i].age;
            }
        }

        // 开辟内存空间，存储次数
        int[] counts = new int[max - min + 1];

        // 统计每个整数出现的次数
        for (int i = 0; i < persons.length; i++) {
            counts[persons[i].age - min]++;
        }

        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 从后往前遍历元素，将它放到有序数组中的合适位置
        Person[] newArray = new Person[persons.length];
        for (int i = persons.length - 1; i >= 0; i--) {
            newArray[--counts[persons[i].age - min]] = persons[i]; //注意:不是persons[i].age
        }

        // 将有序数组赋值到array
        for (int i = 0; i < newArray.length; i++) {
            persons[i] = newArray[i];
        }

        //输出结果
        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i]);
        }
    }




    private static class Person {

        int age;

        String name;

        Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person [age=" + age
                    + ", name=" + name + "]";
        }

    }

}
