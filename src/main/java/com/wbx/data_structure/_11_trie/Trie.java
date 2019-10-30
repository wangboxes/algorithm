package com.wbx.data_structure._11_trie;

import java.util.HashMap;

/**
 * @author wbx
 * @decription: 前缀树
 * 优点：搜索字符串的效率注意跟要被搜索的字符串长度有关
 * 缺点：需要耗费大量的内存（因为它存储的是单词中的每个字符）
 * @date 2019-6-6
 */
public class Trie<V> {

    private int size;

    private Node<V> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public boolean contains(String key) {

        return false;
    }

    public boolean startWith(String prefix) {
        return false;
    }


    public V add(String key, V value) {
        keyCheck(key);

        if (size == 0) {

        }

        return null;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key can not be blank");
        }
    }


    public V remove(String key) {

        return null;
    }


    private static class Node<V> {

        Node<V> parent;
        HashMap<Character, Node<V>> children;
        Character character;
        V value;
        boolean word;//是否是一个单词的结尾


        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }


}
