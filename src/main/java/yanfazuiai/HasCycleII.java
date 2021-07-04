package yanfazuiai;

import java.util.HashSet;
import java.util.Set;

// 找到链表环的入口
// 思路：用set存链表节点，出现重复的元素就是环的入口节点
public class HasCycleII {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    // 思路二：快慢节点
    //        a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)
    //        a=c+(n−1)(b+c)
    public ListNode detectCycle1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}
