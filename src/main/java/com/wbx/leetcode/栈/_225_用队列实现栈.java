package com.wbx.leetcode.栈;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @describe： https://leetcode-cn.com/problems/implement-stack-using-queues/
 * @Date：2019/12/30 0:05
 * @author：wbx
 */
public class _225_用队列实现栈 {

    class MyStack {
        private Queue<Integer> inQueue;//输入队列
        private Queue<Integer> outQueue;//输出队列

        public MyStack() {
            inQueue = new LinkedList<>();
            outQueue = new LinkedList<>();
        }

        public void push(int x) {
            inQueue.offer(x);
            // 将b队列中元素全部转给a队列
            while(!outQueue.isEmpty()) {
                inQueue.offer(outQueue.poll());
            }
            // 交换a和b,使得a队列没有在push()的时候始终为空队列
            Queue temp = inQueue;
            inQueue = outQueue;
            outQueue = temp;
        }

        public int pop() {
            return outQueue.poll();
        }

        public int top() {
            return outQueue.peek();
        }

        public boolean empty() {
            return outQueue.isEmpty();
        }
    }
}
