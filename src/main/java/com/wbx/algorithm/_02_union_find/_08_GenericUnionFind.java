package com.wbx.algorithm._02_union_find;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @describe： 自定义对象的并查集, Quick Union + 基于rank的优化 + Path Halving
 * @Date：2019/10/21 10:19
 * @author：wbx
 */
public class _08_GenericUnionFind<V> {


    private Map<V, Node<V>> nodes = new HashMap<>();

    public void makeSet(V v) {
        if (!nodes.containsKey(v)) {
            nodes.put(v, new Node<>(v));
        }
    }


    public V find(V v) {
        Node<V> node = findNode(v);
        return node == null ? null : node.value;
    }

    /**
     * 找出v的根节点
     */
    private Node<V> findNode(V v) {
        Node<V> node = nodes.get(v);

        if (node == null) {
            return null;
        }

        while (!Objects.equals(node.value, node.parent.value)) {
            node.parent = node.parent.parent;
            node = node.parent;
        }

        return node;
    }

    public void union(V v1, V v2) {
        Node<V> p1 = findNode(v1);
        Node<V> p2 = findNode(v2);
        if (p1 == null || p2 == null) {
            return;
        }
        if (Objects.equals(p1.value, p2.value)) {
            return;
        }

        if (p1.rank < p2.rank) {
            p1.parent = p2;
        } else if (p1.rank > p2.rank) {
            p2.parent = p1;
        } else {
            p1.parent = p2;
            p2.rank += 1;
        }
    }

    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    private static class Node<V> {

        V value;

        Node<V> parent = this;

        int rank = 1;

        Node(V value) {
            this.value = value;
        }
    }
}