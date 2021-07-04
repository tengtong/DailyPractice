package yanfazuiai;

import java.util.*;


// 103. 二叉树的锯齿形层序遍历
// 思路：bfs、duque双端队列
public class ZigzagLevelOrder {

    // 方法一：层序遍历，但是每层的输出结果用双端队列来记录，实现结果的正序或逆序记录
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> retList = new LinkedList<>();
        if (root == null) {
            return retList;
        }
        // 双端队列实现之字顺序
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        // 使用层数来统计当前层的遍历方向，偶数层是正向，奇数层是逆向
        int cengShu = 0;
        TreeNode curNode;
        while (!queue.isEmpty()) {
            int size = queue.size();
            cengShu++;
            // 每层的遍历结果存到双端队列中，通过层数来判断是正向加入，还是逆向加入
            Deque<Integer> curCengdeque = new LinkedList<>();
            while (size-- > 0) {
                curNode = queue.poll();
                if (cengShu % 2 == 0) {
                    curCengdeque.addFirst(curNode.val);
                } else {
                    curCengdeque.addLast(curNode.val);
                }
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            retList.add(new LinkedList<>(curCengdeque));
        }
        return retList;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        ZigzagLevelOrder test = new ZigzagLevelOrder();
        test.zigzagLevelOrder(root);
    }
}
