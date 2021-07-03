package DailyPractice.M05;

import DailyPractice.TreeNode;

import java.util.LinkedList;

// 872. 叶子相似的树
public class M0510 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        LinkedList<Integer> list1 = new LinkedList();
        LinkedList<Integer> list2 = new LinkedList();
        leafSequence(root1, list1);
        leafSequence(root2, list2);
        if (list1.equals(list2)) {
            return true;
        } else {
            return false;
        }
    }
    // 树，递归
    // 递归栈也是dfs的一种实现方式
    public void leafSequence(TreeNode root, LinkedList list) {
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        if (root.left != null) {
            leafSequence(root.left, list);
        }
        if (root.right != null) {
            leafSequence(root.right, list);
        }
    }
}
