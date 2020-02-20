package com.wbx.leetcode.第三季._02_链表;

/**
 * @describe： https://leetcode-cn.com/problems/add-two-numbers/
 * @Date：2020/2/20 22:45
 * @author：wbx
 *
 *
 */
public class _02_2_两数相加 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //创建一条新链表的方式：和_01_203_移除链表元素 一样
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        // 进位数
        int carry = 0;
        while (l1 != null || l2 != null) {
            int v1 = 0;
            if (l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }

            int v2 = 0;
            if (l2 != null){
                v2 = l2.val;
                l2 = l2.next;
            }

            int sum = v1 + v2 + carry;
            //sum % 10 为个位数
            tail = tail.next = new ListNode(sum % 10);
            // 求十位数
            carry = sum / 10;

        }

        if (carry != 0){
            tail.next = new ListNode(carry);
        }

        return dummyHead.next;

    }


}
