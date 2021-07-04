package dailypractice.m06;

import java.util.HashMap;
import java.util.Map;

// 523. 连续的子数组和
public class M0602 {

    // 方法一：暴力法
    // 思路：两个for，头尾指针，前缀和
    // 通过率：93/94
    public boolean checkSubarraySum(int[] nums, int k) {
        for (int start = 0; start < nums.length; start++) {
            int subArrSum = nums[start];
            for (int end = start + 1; end < nums.length; end++) {
                subArrSum += nums[end];
                if (subArrSum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // 方法一：前缀和 + 哈希表
    public boolean checkSubarraySum1(int[] nums, int k) {
        // 前缀和
        int[] sums = new int[nums.length];
        // 同余定理：subNum%k == 0时, sums[j+1]%k == sums[i]%k
        // key: sum[i]%k, value:i
        Map<Integer, Integer> map = new HashMap<>();
        sums[0] = nums[0];
        map.put(nums[0] % k, 0);
        map.put(0, -1); // yuShu == 0
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
            // sums[i]表示nums[0,...,i]
            // 若sums[j]和sums[i]余数都相等
            int yuShu = sums[i] % k;
            if (map.containsKey(yuShu) && i - map.get(yuShu) >= 2){
                return true;
            }
            // 只有在不存在key才添加，且这里只要判断是否存在即可，不用比较长度
            if (!map.containsKey(yuShu)) {
                map.put(yuShu, i);
            }
        }
        return false;
    }

    // 方法三：前缀和 + 哈希表
    // leetcode版
    public boolean checkSubarraySum2(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        // 使用remainder+nums[i]的方式，可以不用将头位置特殊考虑
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            // 这里直接对余数进行累加，除法的分配率
            // (5+4)%4==1, 5%4+4%4==1, 1+4%4=1
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {23,2,4,6,6};
        M0602 test = new M0602();
        System.out.println(test.checkSubarraySum1(nums, 7));
    }
}
