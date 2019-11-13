package com.wbx.algorithm._06_贪心;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @describe： 0-1背包
 * @Date：2019/11/13 16:07
 * @author：wbx
 *
 * ◼ 有 n 件物品和一个最大承重为 W 的背包，每件物品的重量是 w、价值是 v
 * 在保证总重量不超过 W 的前提下，将哪几件物品装入背包，可以使得背包的总价值最大？
 * 注意：每个物品只有 1 件，也就是每个物品只能选择 0 件或者 1 件，因此称为 0-1背包问题
 * ◼ 如果采取贪心策略，有3个方案
 * ① 价值主导：优先选择价值最高的物品放进背包
 * ② 重量主导：优先选择重量最轻的物品放进背包
 * ③ 价值密度主导：优先选择价值密度最高的物品放进背包（价值密度 = 价值 ÷ 重量）
 */
public class _03_01Knapsack {


    public static void main(String[] args) {
        select("价值主导",150, (Article a1, Article a2) -> {
            //价值大的排在前面
            return a2.value - a1.value;
        });
        select("重量主导",150, (Article a1, Article a2) -> {
            //重量轻的排在前面
            return a1.weight - a2.weight;
        });
        select("价值密度主导",150, (Article a1, Article a2) -> {
            //价值密度高的排在前面
            return Double.compare(a2.valueDensity, a1.valueDensity);
        });
    }



    static void select(String title, int capacity, Comparator<Article> cmp) {
        Article[] articles = new Article[] {
                new Article(35, 10), new Article(30, 40),
                new Article(60, 30), new Article(50, 50),
                new Article(40, 35), new Article(10, 40),
                new Article(25, 30)
        };
        Arrays.sort(articles, cmp);
        int totalValue = 0;
        //添加进背包里的重量
        int addedWeight = 0;
        List<Article> selectedArticles = new LinkedList<>();
        for (int i = 0; i < articles.length && addedWeight < capacity; i++) {
            int newWeight = addedWeight + articles[i].weight;

            if (capacity >= newWeight) {
                addedWeight = newWeight;
                totalValue += articles[i].value;
                selectedArticles.add(articles[i]);
            }
        }

        System.out.println("【" + title + "】");
        System.out.println("总价值：" + totalValue);
        System.out.println("总重量：" + addedWeight);
        for (int i = 0; i < selectedArticles.size(); i++) {
            System.out.println(selectedArticles.get(i));
        }
        System.out.println("-----------------------------");


    }


    /**
     * 物品
     */
    static class Article {
        public int weight;
        public int value;
        public double valueDensity;
        public Article(int weight, int value) {
            this.weight = weight;
            this.value = value;
            valueDensity = value * 1.0 / weight;
        }
        @Override
        public String toString() {
            return "Article [weight=" + weight + ", value=" + value + ", valueDensity=" + valueDensity + "]";
        }
    }
}
