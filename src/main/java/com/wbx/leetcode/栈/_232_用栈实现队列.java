package com.wbx.leetcode.栈;

import java.util.Stack;

/**
 * @describe：  https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * @Date：2019/12/29 23:57
 * @author：wbx
 *
 * ◼ 准备2个栈： inStack、 outStack
 * 入队时， push 到 inStack 中
 * 出队时
 * ✓ 如果 outStack 为空，将 inStack 所有元素逐一弹出， push 到 outStack， outStack 弹出栈顶元素
 * ✓ 如果 outStack 不为空， outStack 弹出栈顶元素
 */
public class _232_用栈实现队列 {

    class MyQueue {

        private Stack<Integer> inStack;
        private Stack<Integer> outStack;

        /** Initialize your data structure here. */
        public MyQueue() {
            this.inStack = new Stack<>();
            this.outStack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            inStack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            checkOutStack();
            return outStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            checkOutStack();
            return outStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        private void checkOutStack() {
            if (outStack.isEmpty()){
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }

    }


}
