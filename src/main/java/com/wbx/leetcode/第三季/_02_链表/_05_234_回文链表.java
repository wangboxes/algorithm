package com.wbx.leetcode.第三季._02_链表;

/**
 * @describe： https://leetcode-cn.com/problems/palindrome-linked-list/
 * @Date：2020/2/20 23:41
 * @author：wbx
 *
 * 先将链表分成两半（注意处理链表长度为奇数的情况），将其中一个链表反转，再逐个比较两链表的val值。
 */
public class _05_234_回文链表 {

    public boolean isPalindrome(ListNode head) {
        //空链表和单各个值都是回文
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }

        // 找到中间节点
        ListNode mid = middleNode(head);

        // 翻转右半部分（中间节点的右边部分）
        ListNode rightHead = reverseList(mid.next);
        ListNode leftHead = head;

        // 从lHead、rHead出发，判断是否为回文链表
        while (rightHead != null) {
            if (leftHead.val != rightHead.val) {
                return false;
            }
            rightHead = rightHead.next;
            leftHead = leftHead.next;
        }

        return true;
    }

    /**
     * 找到中间节点（右半部分链表头结点的前一个节点）
     * 比如 1>2>3>2>1中的3是中间节点
     * 比如 1>2>2>1中左边第一个2是中间节点
     * @param head
     * @return
     */
    //快慢指针找到中间节点
    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 翻转链表
     * @param head 原链表的头结点
     * 比如原链表：1>2>3>4>null，翻转之后是：4>3>2>1>null
     * @return 翻转之后链表的头结点（返回4）
     */
    private ListNode reverseList(ListNode head) {
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
