package yanFaZuiAi;

// 反转链表
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode ret = helper(head);
        return ret;
    }
    private ListNode helper(ListNode head) {
        // 首先要判断 head==null，再判断 head.next==null
        if (head == null || head.next == null) {
            return head;
        }
        // 先保留nextNode节点
        ListNode nextNode = head.next;
        // 该递归方法的返回值就是新链表的头节点
        ListNode newHead = helper(head.next);
        // 每一层的逻辑反转
        nextNode.next = head;
        head.next = null;
        // 返回截止条件中更新的值
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(5);
        ListNode listNode2 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = null;
        ReverseList reverseList = new ReverseList();
        ListNode ret = reverseList.reverseList(listNode1);
    }
}
