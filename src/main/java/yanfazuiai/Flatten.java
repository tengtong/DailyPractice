package yanfazuiai;

import java.util.LinkedList;
import java.util.List;

// 114、二叉树展成链表
// 思路：前序遍历
public class Flatten {
    // 方法一：将前序遍历结果放到list中，然后遍历list拼接节点，空间复杂度不为O(1)
    public void flatten(TreeNode root) {
        // 1、先将所有的节点都按前序遍历的顺序存到list中
        List<TreeNode> list = new LinkedList();
        preOrderTraversal(root, list);
        // 2、遍历list，将其中的节点按list的顺序重新进行拼接
        TreeNode pre, cur;
        for (int i = 1; i < list.size(); i++) {
            pre = list.get(i - 1);
            cur = list.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }
    public void preOrderTraversal(TreeNode root, List<TreeNode> list) {
        // 截止条件
        if (root == null) {
            return;
        }
        list.add(root);
        preOrderTraversal(root.left, list);
        preOrderTraversal(root.right, list);
    }

    // 方法二：在树上实现原地算法，就是找到当前节点的前驱节点
    // 思路：判断当前节点的左节点是否为null，不为null的话，找到左子树的最右侧节点，然后将当前节点的右节点拼在其后面
    public void flatten1(TreeNode root) {
        TreeNode curNode = root;
        while (curNode != null) {
            if (curNode.left != null) {
                // 先找到curNode左子树的最右侧节点
                TreeNode leftTreeRightestNode = curNode.left;
                while (leftTreeRightestNode.right != null) {
                    leftTreeRightestNode = leftTreeRightestNode.right;
                }
                leftTreeRightestNode.right = curNode.right;
                curNode.right = curNode.left;
                curNode.left = null;
            }
            curNode = curNode.right;
        }
    }
}
