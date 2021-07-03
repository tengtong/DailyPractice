package DailyPractice.M06;

import yanFaZuiAi.ListNode;

// 160. 相交链表
public class M0604 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        // 若两个链表没有交点，则l1和l2遍历完两个链表时，同时为null时退出
        // 若两个链表有交点，则l1和l2会同时等于链表相交处的节点时退出循环
        while (l1 != l2) {
            l1 = (l1 != null ? l1.next : headB);
            l2 = (l2 != null ? l2.next : headA);
        }
        return l1;
    }
}
