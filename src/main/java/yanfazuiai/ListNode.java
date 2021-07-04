package yanfazuiai;

import java.util.LinkedList;
import java.util.List;

public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    // 从自身节点开始往后打印
    public String ListToString() {
        List retList = new LinkedList();
        ListNode tempHead = this;
        while (tempHead != null) {
            retList.add(tempHead.val);
            tempHead = tempHead.next;
        }
        return retList.toString();
    }
}