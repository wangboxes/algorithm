package com.wbx.leetcode.链表;

/**
 * @author WANGBOXIONG297
 * @decription: https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * @date 2019-4-19
 */
public class _83_删除排序链表中的重复元素 {

    //执行用时 : 2 ms,击败了63.65% 的用户
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        //没有节点或只用一个节点时，肯定不会有重复，直接返回，可以省略这句，后面while循环中有判断了
        /*if (current == null || current.next == null) {
            return head;
        }*/
        while (current != null && current.next != null) {
            //相等说明重复了
            if (current.val == current.next.val) {
                //此时没有改变current指针，届时current.val和current.next.next的val比较是否相等，如果不相等才能移动current指针
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        _83_删除排序链表中的重复元素 t = new _83_删除排序链表中的重复元素();


        t.deleteDuplicates(listNode1);


    }
}
