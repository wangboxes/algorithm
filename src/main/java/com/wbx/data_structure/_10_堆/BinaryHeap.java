package com.wbx.data_structure._10_堆;


import com.wbx.data_structure._06_二叉树.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * @author wbx
 * @decription: 二叉堆
 * @date 2019-5-27
 *
 * 堆的一个重要性质：任意节点的值总是 ≥（≤ ） 子节点的值
 * 如果任意节点的值总是 ≥ 子节点的值，称为： 最大堆、 大根堆、 大顶堆
 * 如果任意节点的值总是 ≤ 子节点的值，称为： 最小堆、 小根堆、 小顶堆
 * ◼ 由此可见，堆中的元素必须具备可比较性（跟二叉搜索树一样）
 *
 * 二叉堆的逻辑结构就是一棵完全二叉树，所以也叫完全二叉堆
 * ◼ 鉴于完全二叉树的一些特性， 二叉堆的底层（物理结构）一般用数组实现即可
 *
 * ◼ 索引 i 的规律（n 是元素数量）
 *
 * 如果 i = 0 ，它是根节点
 *
 * 如果 i > 0 ，它的父节点的索引为 floor( (i – 1) / 2 )
 *
 * 如果 2i + 1 ≤ n – 1，它的左子节点的索引为 2i + 1
 * 如果 2i + 1 > n – 1 ，它无左子节点
 *
 * 如果 2i + 2 ≤ n – 1 ，它的右子节点的索引为 2i + 2
 * 如果 2i + 2 > n – 1 ，它无右子节点
 */
public class BinaryHeap<E> implements Heap<E>, BinaryTreeInfo {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    //堆中的元素比较具备可比性（和二叉搜索树一样）
    private Comparator comparator;

    private E[] elements;


    public BinaryHeap(E[] elements, Comparator comparator) {
        this.comparator = comparator;
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    public BinaryHeap(E[] elements) {
        this(elements,null);
    }

    public BinaryHeap(Comparator comparator) {
        this(null,comparator);
    }

    public BinaryHeap() {
        this(null,null);
    }


    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }


    /**
     * 批量建堆
     */
    private void heapify() {
        // 自上而下的上滤
        /*for (int i = 1; i < size; i++) {
            siftUp(i);
        }*/

        // 自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        //往数组的末尾添加元素
        elements[size] = element;
        //堆化
        siftUp(size);
        size++;

    }

    /**
     * 自下往上堆化(上滤)
     *
     * ◼ 循环执行以下操作:
     * 如果 node ＞ 父节点
     * ✓ 与父节点交换位置
     *
     * 如果 node ≤ 父节点，或者 node 没有父节点
     * ✓ 退出循环
     *
     * 时间复杂度： O(logn)
     *
     * @param index
     */
    private void siftUp(int index) {
        E element = elements[index];
        while (index > 0) {
            int pIndex = (index - 1) >> 1;
            E parent = elements[pIndex];
            if (compare(element, parent) <= 0) {
                break;
            }

            // 将父元素存储在index位置
            elements[index] = parent;

            index = pIndex;
        }
        elements[index] = element;

    }

    private void siftUp1(int index) {
        E element = elements[index];
        while (index > 0) {
            int pIndex = (index - 1) >> 1;
            E parent = elements[pIndex];
            if (comparator.compare(element, parent) <= 0) {
                return;
            }

            // 交换index、pIndex 位置的内容
            E tmp = elements[index];
            elements[index] = parent;
            elements[pIndex] = tmp;

            index = pIndex;
        }
    }


    /**
     * 扩容
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }

        //扩容为原来的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }


    /**
     * 1. 用最后一个节点覆盖根节点
     * 2. 删除最后一个节点
     * 3. 循环执行以下操作(下虑)（图中的 43 简称为 node）
     * 如果 node < 最大的子节点
     * ✓ 与最大的子节点交换位置
     * 如果 node ≥ 最大的子节点， 或者 node 没有子节点
     * ✓ 退出循环
     *
     * @return
     */
    @Override
    public E remove() {
        emptyCheck();
        int lastIndex = --size;
        E root = elements[0];
        //尾元素替代头元素
        elements[0] = elements[lastIndex];
        //删除最后一个节点
        elements[lastIndex] = null;
        if (size != 0) {
            siftDown(0);
        }
        return root;
    }

    /**
     * 下滤
     *
     * @param index
     */
    private void siftDown(int index) {
        E element = elements[index];
        //第一个有叶子节点的索引: 左子节点的索引为 2i + 1 ,如 2i + 1 > size -1 说明有子节点,==> i>(size-2)/2 == size/2 -1  ===> i=size/2 为最后一个有子节点的索引
        int half = size >> 1;

        // 第一个叶子节点的索引 == 非叶子节点的数量
        // index < 第一个叶子节点的索引
        // 必须保证index位置是非叶子节点
        while (index < half) {
            // index的节点有2种情况
            // 1.只有左子节点
            // 2.同时有左右子节点

            // 默认为左子节点跟它进行比较

            //左子节点
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            //右子节点
            int rightIndex = childIndex + 1;
            E rightChild = elements[rightIndex];

            //// 选出左右子节点最大的那个
            if (rightIndex < size && compare(rightChild, child) > 0) {
                childIndex = rightIndex;
                child = rightChild;
            }

            if (compare(element, child) >= 0) {
                break;
            }

            // 将子节点存放到index位置
            elements[index] = child;
            // 重新设置index
            index = childIndex;
        }

        elements[index] = element;

    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            //替换
            elements[0] = element;
            siftDown(0);
        }

        return root;
    }


    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }


    //------------------打印------------------------

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        Integer index = (Integer) node;
        return elements[index];
    }
}
