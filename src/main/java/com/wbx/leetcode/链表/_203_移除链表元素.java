package com.wbx.leetcode.链表;

/**
 * @author WANGBOXIONG297
 * @decription: 删除链表中等于给定值 val 的所有节点。
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @date 2019-4-19
 */
public class _203_移除链表元素 {

    public ListNode removeElements(ListNode head, int val) {
        //先判断要删除的值是否在头部，找到真的head
        while (head != null) {
            if (head.val == val) {
                head = head.next;
            } else {
                break;
            }
        }
        ListNode currentHead = null;
        if (head != null) {
            currentHead = new ListNode(head.val);
        } else {
            return null;
        }
        System.out.println("currentHead = " + currentHead);
        head = head.next;
        while (head != null) {
            if (head.val != val) {
                ListNode temp = currentHead;
                while (temp != null) {
                    if (temp.next == null) {
                        temp.next = new ListNode(head.val);
                        break;
                    }
                    temp = temp.next;
                }
            }

            head = head.next;
        }

        return currentHead;
    }


    public ListNode removeElements2(ListNode head, int val) {
        ListNode header = new ListNode(-1);
        header.next = head;
        ListNode cur = header;
        while(cur.next != null){
            if(cur.next.val == val ){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return header.next;
    }
}
