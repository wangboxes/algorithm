package com.wbx.leetcode.链表;

/**
 * @describe：
 * @Date：2019/4/21 0:18
 * @author：wbx
 */
public class Test {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(2);
        ListNode listNode5 = new ListNode(2);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
//        listNode5.next = listNode3;
        _203_移除链表元素 t = new _203_移除链表元素();


        ListNode head = t.removeElements(listNode1, 2);
        while (head != null){
            System.out.println(head.val);
            head= head.next;
        }


    }
}
