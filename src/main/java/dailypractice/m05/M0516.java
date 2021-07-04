package dailypractice.m05;

import java.util.HashSet;
import java.util.Set;

// 421. 数组中两个数的最大异或值
// 思路：两个数的位不重复位数越少，异或结果越大，简称不进位加法
//      最值，dp？
public class M0516 {

    // 方法一：进制法
    // 异或是相同为0，不同为1的不进位加法，想获得最大的异或值，就要尽量取得高位上异或结果1的两个数
    // 利用TwoSum的思路，a^b=c ==> a^c=b，这里a和b都表示一个位上的二进制值
    // 而且记录下一位时，也会保留上一位可以求的最大值，以此保证最大异或值是从nums中的两个不变的数取得的
    // 通过 1^curNum=exceptNum，查询是否有exceptNum，来确定该位上可以得到1
    // 以此对31个位从高位到低位都进行判断，就可以求出nums中可以获得最大的异或值
    public int findMaximumXOR(int[] nums) {
        int maxResult = 0;
        int mask = 0;
        for (int i = 31; i >=0; --i) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            int tmp = maxResult | (1 << i);
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) {
                    maxResult = tmp;
                    break;
                }
            }
        }
        return maxResult;
    }

    // 方法二：暴力法
    public int findMaximumXOR1(int[] nums) {
        if (nums.length <= 1 || nums == null) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int tempXOR = nums[i] ^ nums[j];
                max = max < tempXOR ? tempXOR : max;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1};
        M0516 test = new M0516();
        int ret = test.findMaximumXOR(arr);
        System.out.println(ret);
    }
}
