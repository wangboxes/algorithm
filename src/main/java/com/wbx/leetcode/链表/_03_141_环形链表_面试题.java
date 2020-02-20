package com.wbx.leetcode.链表;

/**
 * @author WANGBOXIONG297
 * @decription: 给定一个链表，判断链表中是否有环。
 *              https://leetcode-cn.com/problems/linked-list-cycle/
 * @date 2019-4-19
 */
public class _03_141_环形链表_面试题 {

    public boolean hasCycle(ListNode head) {
        //没有节点或只有一个节点且next==null时肯定没有环
        if (head == null || head.next == null){
            return false;
        }
        //慢指针
        ListNode slow = head;
        //快指针(类似跑操场，跑得快的在前面，后面会追上跑得慢的)
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }



}
