package yanFaZuiAi;

//k个节点一组翻转链表
//思路：k个节点翻转类同普通的翻转链表 + 重复这个过程n次，使用递归
public class ReverseKGroup {

    // 方法：对以head为头节点的全部链表（包括其子链表）进行4个一组的翻转，并返回翻转后的新链表的头节点
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tail = head;
        // 先判断剩余节点是否还有k个
        for (int i = 0; i < k; i++) {
            if (tail == null) {
                // 不翻转，直接返回原始的头节点
                return head;
            }
            tail = tail.next;
        }
        // 如果代码能正常走到这里，那么 tail已经是下一组k个节点的头节点
        // 开始对head开始的k个节点进行一组翻转
        ListNode newHead = reverseList(head, tail);
        // !!!
        head.next = reverseKGroup(tail, k);
        return newHead;
    }

    // 从head到tail实现翻转，这里不包括tail节点，左闭右开
    // 返回翻转后链表的新头节点
    // 1->2->3 => 3->2->1
    private ListNode reverseList(ListNode head, ListNode tail) {
        if (head.next == tail) {
            return head;
        }
        ListNode nextNode = head.next;
        ListNode newHead = reverseList(head.next, tail);
        nextNode.next = head;
        // !!!
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ReverseKGroup test = new ReverseKGroup();
        ListNode ret = test.reverseKGroup(head, 2);
    }
}
