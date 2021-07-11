package dailypractice.m06;

public class TreeNode {
    // private 修饰的属性，只有类内部的方法才能访问，进行了封装
    // 如果对象要访问类的内部成员变量，需要通过public的get和set方法
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}