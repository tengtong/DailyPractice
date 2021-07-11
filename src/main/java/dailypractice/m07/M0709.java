package dailypractice.m07;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.10. 主要元素
 * 时间复杂度为 O(N) ，空间复杂度为 O(1)
 * @author tengtong
 */
public class M0709 {
    /**
     * hashMap
     * 主要元素：出现超过一半的个数
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            if (count * 2 > nums.length) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * 方法二：Boyer-Moore 投票算法 / 摩尔投票
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        int candidate = -1;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        // 验证候选人是否为最终目标
        count = 0;
        int length = nums.length;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        return count * 2 > length ? candidate : -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,5,9,5,9,5,5,5};
        M0709 test = new M0709();
        System.out.println(test.majorityElement1(nums));
    }

}