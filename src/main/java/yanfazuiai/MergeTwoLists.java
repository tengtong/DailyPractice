package yanfazuiai;

//合并有序链表
//思路：相同结构、相同操作可以采用递归实现
public class MergeTwoLists {
    //设定，合并是升序的
    public ListNode mergeTwoLists (ListNode l1, ListNode l2) {
         return helper(l1, l2);
    }
    public ListNode helper (ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1 == null && l2 == null) {
            return null;
        }
        //如果l1.value < l2.value
        if (l1.val < l2.val) {
            //那么需要判断一下，l1.next后面接的是 l1.next和l2较小值
            l1.next = helper(l1.next, l2);
            //注意这里，上面拼接完l1.next，那么就应该返回拼接完的l1节点，而不是l2
            return l1;
        } else {
            l2.next = helper(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode l1Head = new ListNode(1);
        l1Head.next = new ListNode(2);
        l1Head.next.next = new ListNode(4);
        ListNode l2Head = new ListNode(2);
        l2Head.next = new ListNode(3);
        l2Head.next.next = new ListNode(5);

        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        ListNode ret = mergeTwoLists.mergeTwoLists(l1Head, l2Head);
    }
}
