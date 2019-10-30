package com.wbx.leetcode.链表;

/**
 * @describe： 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点。
 *              https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * @Date：2019/4/21 19:50
 * @author：wbx
 */
public class _876_链表的中间结点 {

    public ListNode middleNode(ListNode head) {
        ListNode cur = head;
        //先统计总共有几个节点
        int count = 0;
        while (head != null){
            count++;
            head = head.next;
        }
        //取中间节点
        int middleIndex = count / 2;
        //获取对应index的节点
        for (int i = 0; i < middleIndex; i++) {
             cur = cur.next;
        }

        return cur;
    }


}
