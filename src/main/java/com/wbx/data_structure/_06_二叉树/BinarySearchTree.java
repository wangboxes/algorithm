package com.wbx.data_structure._06_二叉树;


import com.wbx.data_structure._06_二叉树.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author WANGBOXIONG297
 * @decription: 二叉搜索树 v1
 * @date 2019-5-17
 * <p>
 * 注意：二叉搜索树存储的元素比较具备可比较性，比如int，double等，如果自定义类型需要指定比较方式，不允许为null
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private int size;

    private Node<E> root;

    private Comparator comparator;


    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }


    public int size() {

        return size;
    }


    public boolean isEmpty() {

        return size == 0;
    }


    public void clear() {
        root = null;
        size = 0;
    }


    /**
     * 添加步骤：
     * 1.找到父节点
     * 2.创建新节点
     * 3.parent.left = node 或 parent.right = node
     * 4.遇到相同的节点，采用覆盖旧值
     */
    public void add(E element) {
        elementNotNullCheck(element);
        // 添加第一个节点
        if (root == null) {
            root = new Node<E>(element, null);
            size++;
            return;
        }

        // 添加的不是第一个节点
        // 找到父节点
        Node<E> parent = null;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { //相等
                node.element = element; //覆盖
                return;
            }
        }

        // 看看插入到父节点的哪个位置
        Node<E> newNode = new Node<>(element, parent);
        if (compare(element, parent.element) > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    /**
     * 元素比较方案设计：
     * 1.允许外界传入一个Comparator（int compare(T o1, T o2)）自定义比较逻辑
     * 2.如果没有传入Comparator，强制认为元素实现了Comparable接口（int compareTo(T o)方法）
     *
     * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        } else {
            return ((Comparable<E>) e1).compareTo(e2);
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * @param element 1.删除叶子节点：直接删除
     *                2.删除度为1的节点：用子节点替代原节点的位置
     *                3.删除度为2的节点：先用前驱或后继节点覆盖原节点的值，再删除相应的前驱或后继节点（前驱，后继节点的度只可能是0或1-->转换为删除度为1或0的节点
     */
    public void remove(E element) {
        remove(node(element));

    }

    private void remove(Node<E> node) {
        if (node == null) return;
        size--;
        if (node.hasTwoChildren()) {// 度为2的节点
            // 找到后继节点
            Node<E> successor = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = successor.element;//替换
            // 删除后继节点
            node = successor;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;//找到真正要被删除的节点

        if (replacement != null) {// node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) {// node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;

            } else {// node == node.parent.right
                node.parent.right = replacement;
            }

        } else if (node.parent == null) {// node是叶子节点并且是根节点
            root = null;
        } else {// node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {// node == node.parent.right
                node.parent.right = null;
            }
        }
    }

    /**
     * 查找后继节点
     *
     * @param node
     * @return
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) return null;

        // 后继节点在右子树当中（right.left.left.left....）
        Node<E> s = node.right;
        if (s != null) {
            while (s.left != null) {
                s = s.left;
            }
            return s;
        }

        // 从父节点、祖父节点中寻找后继节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 查找前驱节点
     *
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        // 前驱节点在左子树当中（left.right.right.right....）
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 根据元素内容获取节点
     *
     * @param element
     * @return
     */
    private Node<E> node(E element) {
        elementNotNullCheck(element);
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else { // cmp < 0
                node = node.left;
            }
        }
        //没有找到返回null
        return null;
    }

    public boolean contains(E element) {

        return node(element) != null;
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;
        visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;
        inorderTraversal(node.left, visitor);
        visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        postorderTraversal(root, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        visitor.visit(node.element);
    }

    /**
     * 层序遍历
     */
    public void levelTraversal(Visitor<E> visitor) {
        if (visitor == null || root == null) return;

        //用队列来实现层序遍历
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> pollNode = queue.poll();
            visitor.visit(pollNode.element);

            if (pollNode.left != null) {
                queue.offer(pollNode.left);
            }

            if (pollNode.right != null) {
                queue.offer(pollNode.right);
            }
        }
    }


    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        //构造方法设计：添加的时候肯定是知道element和parent的(特别的：添加根节点parent为null)，left和right不能知道。
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }


        public boolean isLeaf() {
            return left == null && right == null;
        }


        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    public abstract static class Visitor<E> {
        boolean stop;

        /**
         * @param element
         * @return 如果返回true，就代表停止遍历
         */
        public abstract boolean visit(E element);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;

        toString(node.left, sb, prefix + "L---");
        sb.append(prefix).append(node.element).append("\n");
        toString(node.right, sb, prefix + "R---");
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }


}
