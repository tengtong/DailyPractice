package yanfazuiai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//层序遍历-bfs
public class LevelOrder {
    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        if (root == null) {
            return null;
        }
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList curLevelList = new ArrayList<Integer>();
            while (size-- > 0) {
                TreeNode curNode = queue.poll();
                curLevelList.add(curNode.val);
                if (curNode.left != null) { queue.add(curNode.left); }
                if (curNode.right != null) { queue.add(curNode.right); }
            }
            ret.add(curLevelList);
        }
        return ret;
    }
}
