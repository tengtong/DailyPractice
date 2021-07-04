package dailypractice.m05;

public class M0528 {

    // 方法一：暴力法，通过率80%
    public int totalHammingDistance(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                ret += hammingDistance(nums[i], nums[j]);
            }
        }
        return ret;
    }
    // 计算两个数的汉明距离
    public int hammingDistance(int x, int y) {
        int xXorY = x ^ y;
        int count = 0;
        while (xXorY > 0) {
            count += xXorY & 1;
            xXorY = xXorY >> 1;
        }
        return count;
    }

    // 方法二：逐位统计法
    // 思考：方法一虽然是两个for循环，但是也没有进行重复运算
    //      空间换时间，先将部分结果计算出来
    //      但是1^2, 2^3都是独立，异或结果并不重复，不能用前缀和
    //      利用之前笔试题的思路，1个1个比较，不如直接全部一起比较
    // 思路：异或在2个元素统计距离好用，但是多个元素同时统计时，直接统计位效率更高
    //      nums中，该位上，一共有c个1，n-c个0，则该位的汉明距离就是c(n-c)
    public int totalHammingDistance1(int[] nums) {
        // int [-2^31, 2^31-1]
        int ret = 0;
        // 因为题目说明num在[0, 10^9], 10^9<2^30，故我们从0位枚举到29位即可
        for (int i = 0; i < 30; i++) {
            int count = 0;
            for (int num : nums) {
                count += (num >> i) & 1;
            }
            ret += count * (nums.length - count);
        }
        return ret;
   }

    public static void main(String[] args) {
        int[] nums = {4,14,2};
        M0528 test = new M0528();
        System.out.println(test.totalHammingDistance1(nums));
    }
}
