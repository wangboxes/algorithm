package com.wbx.data_structure._06_二叉树;

import com.example.wbx_demo.my_数据结构._06_二叉树.printer.BinaryTrees;

/**
 * @author WANGBOXIONG297
 * @decription:
 * @date 2019-5-17
 */
public class BinarySearchTreeTest {


    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Integer[] data = {61, 43, 68, 93, 10, 67, 58, 98, 15, 84, 53, 41};
        for (Integer d : data) {
            bst.add(d);
        }
        BinaryTrees.println(bst);
        System.out.println("-----------");
        bst.levelTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });

    }
    private static void test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Integer[] data = {61, 43, 68, 93, 10, 67, 58, 98, 15, 84, 53, 41};
        for (Integer d : data) {
            bst.add(d);
        }
        BinaryTrees.println(bst);
        System.out.println("-----------");

        bst.remove(41);
        BinaryTrees.println(bst);
        System.out.println("-----------");
        bst.remove(43);
        BinaryTrees.println(bst);
        System.out.println("-----------");
        bst.remove(10);
        BinaryTrees.println(bst);
        /*BinarySearchTree.Node<Integer> predecessor = bst.successor(bst.node(61));
        System.out.println(predecessor.element);*/

    }



}
