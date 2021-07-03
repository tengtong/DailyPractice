package yanFaZuiAi;

// 236. 二叉树的最近公共祖先
// 思路：遍历树，判断p和q是否在当前节点root的左右子树中，如果在，则当前节点root就是最近公共祖先
//      如果不在，则继续遍历左右节点，如果当前节点==p||q，则p||q就是最近公共祖先
public class LowestAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        TreeNode ret = helper(root, p, q);
        return ret;
    }
    // 先判断p、q是否都在同一侧子树中
    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p) return p;
        if (root == q) return q;
        TreeNode leftNode = helper(root.left, p, q);
        TreeNode rightNode = helper(root.right, p, q);
        // 如果左边不为空，右边为空，则在左边
        if (leftNode == null) return rightNode;
        if (rightNode == null) return leftNode;
        return root;
    }

    // 牛课网题，输入为int
    // 这里不同于上面，在helper中，需要先判断root为null，其他思路一致
    public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        int ret = helper1(root, o1, o2).val;
        return ret;
    }
    // 先判断p、q是否都在同一侧子树中
    private TreeNode helper1(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) return root;
        TreeNode leftNode = helper1(root.left, p, q);
        TreeNode rightNode = helper1(root.right, p, q);
        // 如果左边不为空，右边为空，则在左边
        if (leftNode == null) return rightNode;
        if (rightNode == null) return leftNode;
        return root;
    }
}
