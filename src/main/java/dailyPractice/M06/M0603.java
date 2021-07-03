package dailyPractice.M06;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

// 525. 连续数组
// 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度
// 最值：dp
public class M0603 {

    // 方法一：前缀和 + 哈希表
    public int findMaxLength(int[] nums) {
        // 遍历数组，然后统计数组中所有的出现的0和1次数
        // key: cha, value: index
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        // cha: 1的个数 - 0的个数
        int cha = 0;
        // 前缀和统计子数组
        // map里记录子数组中（1的个数-0的个数）的差值
        for (int i = 0; i < nums.length; i++) {
           cha = nums[i] == 0 ? --cha : ++cha;
           if (cha == 0) {
               maxLength = Math.max(maxLength, i + 1);
           }
           if (map.containsKey(cha)) {
               maxLength = Math.max(maxLength, i - map.get(cha));
           } else {
               map.put(cha, i);
           }
        }
        return maxLength;
    }

    // 方法二：前缀和 + 哈希表（leetcode版）
    public int findMaxLength1(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        M0603 test = new M0603();
        int[] nums = {0, 1, 0};
        System.out.println(test.findMaxLength(nums));
    }
}
