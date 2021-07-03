package yanFaZuiAi;

import java.util.ArrayList;
import java.util.List;

//分别按照二叉树先序，中序和后序打印所有的节点
public class ThreeOrders {
    public int[][] threeOrders (TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>(3);
        for ( int i = 0; i < 3; i++) {
            ret.add(new ArrayList<>());
        }
        preOrders(root, ret.get(0));
        inOrders(root, ret.get(1));
        postOrders(root, ret.get(2));

        //将list转化成数组
        int[][] retArr = new int[3][ret.get(0).size()];
        retArr[0] = ret.get(0).stream().mapToInt(i->i).toArray();
        retArr[1] = ret.get(1).stream().mapToInt(i->i).toArray();
        retArr[2] = ret.get(2).stream().mapToInt(i->i).toArray();
        return retArr;
    }
    public void preOrders (TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrders(root.left, list);
        preOrders(root.right, list);
    }
    public void inOrders (TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrders(root.left, list);
        list.add(root.val);
        inOrders(root.right, list);
    }
    public void postOrders (TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postOrders(root.left, list);
        postOrders(root.right, list);
        list.add(root.val);
    }
}
