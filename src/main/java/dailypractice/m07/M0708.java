package dailypractice.m07;

import java.util.HashMap;
import java.util.Map;

/**
 * 930. 和相同的二元子数组
 * @author tengtong
 */
public class M0708 {

    /**
     * 方法一：前缀和（找出一组累加和为goal的子数组） + hashMap（统计符合条件的子数组个数）
     * @param nums
     * @param goal
     * @return
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0;
        // key: sum前缀和，value：出现次数
        Map<Integer, Integer> countMap = new HashMap();
        int ret = 0;
        for (int num : nums) {
            countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);
            // sum为前缀和
            sum += num;
            // 判断之前是否有出现过和sum和goal的差值，如果出现就是一组子数组结果
            // [1,1,0,3,2,1], 3-1=2, 从index=0或1开始，到index=3，这个区间就是一组累加和为goal的子数组
            ret += countMap.getOrDefault(sum - goal, 0);
        }
        return ret;
    }

    /**
     * 方法二：双指针/滑动窗口
     * 子数组之和
     * @param nums
     * @param goal
     * @return
     */
    public int numSubarraysWithSum1(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int ret = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            ret += left2 - left1;
            right++;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {1,0,1,0,1};
        M0708 test = new M0708();
        System.out.println(test.numSubarraysWithSum1(nums, 2));
    }
}
