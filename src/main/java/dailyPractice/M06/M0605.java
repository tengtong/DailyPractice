package dailyPractice.M06;

import yanFaZuiAi.ListNode;

// 203. 移除链表元素
public class M0605 {

    // 方法一：迭代法
    public ListNode removeElements(ListNode head, int val) {
        // 链表技巧，哑节点
        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode pre = hair;
        ListNode tempNode = hair;
        while (hair != null) {
            boolean flag = false;
            // 先找到下一个节点val不为目标值的节点
            while (hair != null && hair.val == val) {// 败笔，内置循环，其实一个个节点判断也可以
                hair = hair.next;
                flag = true;
            }
            if (flag == true) {
                // 删除中间节点
                pre.next = hair;
            }
            // 跳出while循环时，hair.val != val
            // 重制pre指针
            pre = hair;
            // 链表循环
            if (hair == null) break;// 败笔
            hair = hair.next;
        }
        return tempNode.next;
    }

    // 方法二：迭代法优化
    public ListNode removeElements1(ListNode head, int val) {
        // 哑节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        // 不一定要判断当前节点，也可以判断下一个节点，这样就需要pre指针了
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }

    // 方法二：递归法
    public ListNode removeElements2(ListNode head, int val) {
        // 截止条件
        if (head == null) {
            return head;
        }
        // 第一层逻辑
        // 逐个节点进行判断，然后在返回值进行选择返回，以此完成拼接
        head.next = removeElements2(head.next, val);
        // 这里与上面迭代的逻辑相同，主要考虑当前节点是val值时，删除当前节点即可，然后判断下一节节点
        return head.val == val ? head.next : head;
    }
}
