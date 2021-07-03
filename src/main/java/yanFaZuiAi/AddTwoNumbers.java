package yanFaZuiAi;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// 445. 两数相加 II
// 思路：倒序链表、栈、递归
public class AddTwoNumbers {

    // 方法一：栈
    // 优化：栈为空时取值的处理
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stackL1 = new Stack();
        Stack<Integer> stackL2 = new Stack();
        // 也可以使用双端队列实现栈
        // Deque<Integer> stack3 = new LinkedList<>();
        while (l1 != null) {
            stackL1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stackL2.push(l2.val);
            l2 = l2.next;
        }
        int a = 0;
        int b = 0;
        int carry = 0;
        int curVal = 0;
        ListNode curNode = null;
        ListNode preNode = null;
        // 只要有一个不为空的栈，就进入while循环
        while (!stackL1.isEmpty() || !stackL2.isEmpty() || carry!= 0) {
            a = stackL1.isEmpty() ? 0 : stackL1.pop();
            b = stackL2.isEmpty() ? 0 : stackL2.pop();
            curVal = (a + b + carry) % 10;
            carry = (a + b + carry) / 10;
            curNode = new ListNode(curVal);
            curNode.next = preNode;
            preNode = curNode;
        }
        return curNode;
    }

    // 方法二：递归栈
    // 可能两个链表长度不一致，递归的栈深度也不一致，所以不易用同一个递归函数来实现全部逻辑
    //     public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
    //     }
    //     private int helper(ListNode l1, ListNode l2) {
    //         if (l1.next == null && l2.next == null) {
    //             return l1.val + l2.val;
    //         }
    //     }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        AddTwoNumbers test = new AddTwoNumbers();
        ListNode ret = test.addTwoNumbers(l1, l2);
        System.out.println(ret.ListToString());
    }
}
