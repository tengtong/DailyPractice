package dailyPractice.M04;

import dailyPractice.TreeNode;

import java.util.*;

//897.递增顺序搜索树
//思路：先中序遍历，然后将重新组织结果
public class M0425 {
    public dailyPractice.TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        inOrder(root, list);
        TreeNode tempNode = new TreeNode(-1);
        TreeNode currentNode = tempNode;
        for (int i : list) {
            currentNode.right = new TreeNode(i);
            currentNode = currentNode.right;
        }
        return tempNode.right;
    }
    private void inOrder (TreeNode root, List<Integer> list) {
        //截止条件
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}
