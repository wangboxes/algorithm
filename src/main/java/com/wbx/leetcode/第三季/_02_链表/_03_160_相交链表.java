package com.wbx.leetcode.第三季._02_链表;

/**
 * @describe： 这三题答案一样
 * 160_相交链表 : https://leetcode-cn.com/problems/intersection-of-two-linked-lists/comments/
 * 面试题 02.07. 链表相交: https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/
 * 面试题52. 两个链表的第一个公共节点 : https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 *
 * @Date：2020/2/20 23:06
 * @author：wbx
 *
 */
public class _03_160_相交链表 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;

        //地址值相等的地方就是相交的地方（注意： null == null ---> true）
        while (curA != curB){ //比较地址值是否相等
            //关键思路：把链表A和链表B合在一起，这样就有一对一地比较是否地址值相等
            curA = (curA != null) ? curA.next : headB;
            curB = (curB != null) ? curB.next : headA;
        }

        return curA;

    }


}
