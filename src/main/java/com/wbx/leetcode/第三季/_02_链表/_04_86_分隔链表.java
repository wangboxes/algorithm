package com.wbx.leetcode.第三季._02_链表;

/**
 * @describe： https://leetcode-cn.com/problems/partition-list/
 * @Date：2020/2/20 23:18
 * @author：wbx
 *
 * 总体思路：自己新建两条链表，然后串起来
 */
public class _04_86_分隔链表 {

    public ListNode partition(ListNode head, int x) {
        //左边的链表：小于 x 的节点
        ListNode leftHead = new ListNode(-1);
        ListNode leftTail = leftHead;

        //右边的链表：大于或等于 x 的节点
        ListNode rightHead = new ListNode(-1);
        ListNode rightTail = rightHead;

        while (head != null) {
            if (head.val < x){
                leftTail = leftTail.next = head;

            } else {
                rightTail = rightTail.next = head;
            }

            head = head.next;
        }

        //注意点：如果没有这行代码，当大于或等于 x 的节点不是最后的节点时，会多串小于x的值
        rightTail.next = null;

        //把左右两条链表串在一起
        leftTail.next = rightHead.next;

        return leftHead.next;

    }
}
