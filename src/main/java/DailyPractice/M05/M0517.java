package DailyPractice.M05;

import javafx.util.Pair;
import yanFaZuiAi.Test;
import yanFaZuiAi.TreeNode;

import java.lang.annotation.Target;
import java.nio.channels.Pipe;
import java.util.LinkedList;
import java.util.Queue;

// 993. 二叉树的堂兄弟节点
// 思路：树的搜索，bfs，dfs
//      判断两个树的节点是否为堂兄弟节点 => 判断两个节点的深度和父节点
// 疑问：树的递归和dfs有什么区别？
public class M0517 {

    // 方法一：找到val为x和y的两个节点，并记录其父节点，对二叉树进行搜索，dfs
    // 优化：可以同时搜索val值为x、y的两个节点，对一些情况进行减枝，避免重复搜索，减少时间复杂度
    public boolean isCousins(TreeNode root, int x, int y) {
        Pair xNode = dfs(root, null, x, 1);
        Pair yNode = dfs(root, null, y, 1);
        if (xNode.getKey().equals(yNode.getKey()) && !xNode.getValue().equals(yNode.getValue())) {
            return true;
        } else {
            return false;
        }
    }
    // dfs搜索val值target的节点，如果找到返回<depth, root>
    private Pair<Integer, TreeNode> dfs(TreeNode root, TreeNode preNode, int target, int depth) {
        // 截止条件
        if (root == null) {
            return null;
        }
        if (root.val == target) {
            return new Pair(depth, preNode);
        }
        // 当前层逻辑
        Pair<Integer, TreeNode> leftNode = dfs(root.left, root, target, depth + 1);
        Pair<Integer, TreeNode> rightNode = dfs(root.right, root, target, depth + 1);
        return leftNode == null ? rightNode : leftNode;
    }

    // 方法二：找到val为x和y的两个节点，并记录其父节点，对二叉树进行搜索，bfs
    public boolean isCousins1(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int depth = -1;
        // <深度，父节点>
        Pair<Integer, TreeNode> xNode = new Pair<>(1, root);
        Pair<Integer, TreeNode> yNode = new Pair<>(1, root);
        while (!queue.isEmpty()) {
            int size  = queue.size();
            depth++;
            while (size-- > 0) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    if (curNode.left.val == x) xNode = new Pair<>(depth, curNode);
                    if (curNode.left.val == y) yNode = new Pair<>(depth, curNode);
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    if (curNode.right.val == x) xNode = new Pair<>(depth, curNode);
                    if (curNode.right.val == y) yNode = new Pair<>(depth, curNode);
                    queue.add(curNode.right);
                }
            }
        }
        if (xNode.getKey().equals(yNode.getKey()) && !xNode.getValue().equals(yNode.getValue())) {
            return true;
        } else {
            return false;
        }
    }

    // 方法三：层序遍历(bfs)，判断x、y是否存在同一层中，如果某一层只出现了x或y那fasle
    //       同时对每个节点的左右节点都进行判断，如果同时出现了x和y也是false
    //       此外，如果某一层中同时都出现了x和y，则是true
    public boolean isCousins2(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        // flag表示x和y出现的次数
        int flag = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null && curNode.right != null) {
                    if ((curNode.left.val == x && curNode.right.val == y) ||
                            (curNode.left.val == y && curNode.right.val == x)) {
                        return false;
                    }
                }
                if (curNode.val == x || curNode.val == y) {
                    flag++;
                }
                if (curNode.left != null) queue.add(curNode.left);
                if (curNode.right != null) queue.add(curNode.right);
            }
            if (flag == 1) {
                return false;
            } else if (flag == 2) {
                return true;
            }
            flag = 0;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        M0517 test = new M0517();
        System.out.println(test.isCousins(root, 5, 4));
    }
}
