package yanfazuiai;

import java.util.Stack;

// 19. 删除链表的倒数第 N 个结点
// 思路：双指针，找到倒数第 N+1 个节点
public class RemoveNthFromEnd {

    // 方法一：快慢指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 链表技巧-哑节点，这样不用特殊考虑头节点
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode fastNode = head;
        // 慢指针要从hair开始走，将head节点也考虑进去
        ListNode slowNode = hair;
        // 找到正数第 n+1 个节点，需要 n 次next
        for (int i = 0; i < n; ++i) {
            fastNode = fastNode.next;
        }
        // 找到倒数第 n+1 个节点
        // 因为slowNode从hair开始走，故要fastNode为null时，slowNode才能走到倒数第 n+1 个节点
        while (fastNode != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        // 删除倒数第 n 个节点
        slowNode.next = slowNode.next.next;
        return hair.next;
    }

    // 方法二：栈
    // 思路：先将全部节点存到栈中，再pop n+1个，以此找到倒数第 n+1 个节点
    //      用栈实现链表的 倒序遍历，在翻转链表中同样适用
    //      用stack和map维护ListNode对象，和用List维护map对象一样，都是用第二个数据结构来维护数据，以此可以使用两个数据结构的特性，好评
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode tempNode = hair;
        // 将全部节点都存到栈中
        while (tempNode != null) {
            stack.push(tempNode);
            tempNode = tempNode.next;
        }
        // 找到倒数的第 n+1 个节点
        ListNode node = new ListNode(0);
        for (int i = 0; i < n + 1; ++i) {
            node = stack.pop();
        }
        node.next = node.next.next;
        return hair.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        RemoveNthFromEnd test = new RemoveNthFromEnd();
        ListNode ret = test.removeNthFromEnd(head,2);
        System.out.println(ret.ListToString());
    }


}
