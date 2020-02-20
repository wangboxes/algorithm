package com.wbx.leetcode.链表;

/**
 * @describe： https://leetcode-cn.com/problems/reverse-linked-list/
 * @Date：2019/12/29 16:55
 * @author：wbx
 */
public class _02_206_反转链表_面试题 {

    /**
     * 递归解法(先确定好函数的功能)
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    /**
     * 迭代解法（头插法）
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = null;
        while (head != null){
            //注意代码有规律：上一行的右边就是下一行的左边
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;

    }


}
