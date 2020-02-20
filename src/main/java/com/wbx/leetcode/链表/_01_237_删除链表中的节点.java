package com.wbx.leetcode.链表;

/**
 * @describe：   请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 *              https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @Date：2019/4/20 19:49
 * @author：wbx
 */
public class _01_237_删除链表中的节点 {

    //执行用时 : 1 ms, 在Delete Node in a Linked List的Java提交中击败了85.18% 的用户
    public void deleteNode(ListNode node) {
        //用下一个节点的值覆盖掉要删除节点的值，然后把删除节点对的next指向删除节点的next.next
        node.val = node.next.val;
        node.next = node.next.next;
    }


}
