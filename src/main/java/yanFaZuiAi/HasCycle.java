package yanFaZuiAi;

// 判断链表是否有环
// 思路_判断链表有环：1、快慢指针相遇
// 思路_找到两个链表的交点：1、两个链表相连判断是否有环 2、比较两个链表的尾节点是否一致 3、a+b=b+a
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        //快慢两个指针
        ListNode slow = head;
        ListNode fast = head;
        // 如果有环，一快一慢就一定会相遇
        // 如果没有环，那么快指针就会先走到链表的尾部，然后结束循环
        while (fast != null && fast.next != null) {
            //慢指针每次走一步
            slow = slow.next;
            //快指针每次走两步
            fast = fast.next.next;
            //如果相遇，说明有环，直接返回true
            if (slow == fast) {
                return true;
            }
        }
        //否则就是没环
        return false;
    }
}
