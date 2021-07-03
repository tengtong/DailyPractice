package DailyPractice.M05;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// 740. 删除并获得点数
// 思路：打家劫舍，小偷不能偷取相邻的房屋，与这题思路一致
public class M0505 {

    // 方法一：最值问题 == dp
    public int deleteAndEarn(int[] nums) {
        // 求出nums数组的最大值
        int maxVal = 0;
        for (int val : nums) {
            maxVal = Math.max(maxVal, val);
        }
        // 构建sum数组，其中index==val，sun[val]为val*fre
        int[] sum = new int[maxVal + 1];
        for (int val : nums) {
            sum[val] += val;
        }
        // 对sum数组进行打家劫舍
        return rob(sum);
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    // 方法二：暴力法，用map统计，然后逐个删除，但是会出现 ConcurrentModificationException问题，即在遍历的时候，修改、删除了元素
    //       暂不推荐
    public int deleteAndEarn1(int[] nums) {
        // Map<arr[i], frequency >
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        Set<Integer> numsSet = new HashSet();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            numsSet.add(num);
        }
        int earnScore = 0;
        int maxScore = 0;
        for (int num : numsSet) {
            earnScore = deleteAndEarnByNum(nums, num, map);
            maxScore = Math.max(maxScore, earnScore);
        }
        return maxScore;
    }
    private int deleteAndEarnByNum(int[] nums, int key , Map<Integer, Integer> map) {
        // Map tempMap = map; 浅拷贝，拷贝的只是引用地址
        Map<Integer, Integer> tempMap = new HashMap<>(map);
        int earnSocre = key * tempMap.get(key);
        tempMap.remove(key);
        tempMap.remove(key + 1);
        tempMap.remove(key - 1);
        // 遍历map有四种方法是
        // entrySet、keySet、valueSet、Iterator，想要在遍历的时候删除元素，就需要使用Iterator方法
        Iterator<Map.Entry<Integer, Integer>> it = tempMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            earnSocre += entry.getKey() * entry.getValue();
            tempMap.remove(entry.getKey());
            tempMap.remove(entry.getKey() + 1);
            tempMap.remove(entry.getKey() - 1);
        }
        return earnSocre;
    }

    public static void main(String[] args) {
        int[] nums = {3,4,2};
        M0505 test = new M0505();
        System.out.println(test.deleteAndEarn(nums));
    }
}
