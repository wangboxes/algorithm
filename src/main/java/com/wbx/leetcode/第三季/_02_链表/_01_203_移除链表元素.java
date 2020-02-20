package com.wbx.leetcode.第三季._02_链表;


/**
 * @describe： https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @Date：2020/2/20 16:29
 * @author：wbx
 * 总体思路：把它想象成在构建一条新链表
 */
public class _01_203_移除链表元素 {

    //没有用虚拟头结点的情况，要多很多判断
    //	public ListNode removeElements(ListNode head, int val) {
//		if (head == null) return null;
//
//		// 新链表的头结点
//		ListNode newHead = null;
//		// 新链表的尾结点
//		ListNode newTail = null;
//
//		while (head != null) {
//			if (head.val != val) {
//				// 将head拼接到newTail的后面
//				if (newTail == null) {
//					newHead = head;
//					newTail = head;
//				} else {
//					newTail.next = head;
//					newTail = head;
//				}
//			}
//			head = head.next;
//		}
//		if (newTail == null) {
//			return null;
//		} else {
//			// 尾结点的next要清空
//			newTail.next = null;
//		}
//		return newHead;
//	}

    public ListNode removeElements(ListNode head, int val) {
        //虚拟头结点
        ListNode newHead = new ListNode(-1);
        ListNode newTail = newHead;

        while (head != null) {
            if (head.val != val) {
                newTail.next = head;
                newTail = head;
            }
            head = head.next;
        }

        //这个要注意：如不没有处理，后面如果都是删除的元素，结果会不正确
        newTail.next = null;

        return newHead.next;
    }

}
