package dailyPractice.M05;

// 1486. 数组异或操作
public class M0507 {

    // 方法一：方法二的优化版
    public int xorOperation(int n, int start) {
        int ret = 0;
        for (int i = 0; i < n; i++) {
            // 0 ^ num = num // 0与任何数异或都是本身
            ret ^= (start + 2 * i);
        }
        return ret;
    }

    // 方法二：直接思路版，空间复杂度和异或运算可以优化，见方法一
    public int xorOperation1(int n, int start) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = start + 2 * i;
        }
        int ret = nums[0];
        for (int i = 1; i < n; i++) {
             ret ^= nums[i];
        }
        return ret;
    }
}
