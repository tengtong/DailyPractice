package yanfazuiai;

import java.util.Arrays;

// 300. 最长递增子序列 Longest Increasing Subsequence
public class LIS {

    // 方法一：暴力法，双层循环，找出所有的数组中所有的子序列

    // 方法一：动态规划，时间复杂度为O(n^2)
    // dp[i]的设定：大问题，最长的递增子序列长度；小问题：dp[i]以nums[i]为结尾的最长递增序列长度，这个子序列里包括nums[i]
    // 因为dp[i]是以nums[i]为结尾的子序列，如果要更新dp[j]，只要保证nums[j] > nums[i]即可
    //    即只需要考虑dp[i+1]和dp[i]的关系，不需要考虑整个前面i个的整体情况
    //    这里和递归思想有些类同
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        // dp[i]表示以nums[i]为结尾的最长递增子序列的长度，但是dp[i]不一定是dp数组中最长的
        //    所以最后要对dp数组max一下
        // 因为dp[i]表示，以nums[i]为结尾的最长子序列长度
        //    那么以nums[i]为结尾的数组[0,..,i]中也多种递增序列
        //    例如：[0,1,2], {0,1},{0,2},{0,1,2}都是递增子序列
        //    故当前添加一下nums[i]时，需要遍历[0,...,i-1]，如果比其nums[x]大，dp[i]的长度就是nums[x]序列dp[x]上加1
        int[] dp = new int[length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 找出dp数组中的最大值
        return Arrays.stream(dp).max().getAsInt();
    }

    // 方法二：贪心 + 二分
    // 贪心：升子序列尽可能的长，让序列上升得尽可能慢，让添加在序列末端的值尽可能小
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;
        for (int num : nums) {
            int index = binarySearch(tails, len, num);
            tails[index] = num;
            if (index == len) {
                len++;
            }
        }
        return len;
    }
    private int binarySearch(int[] tails, int len, int key) {
        int l = 0, h = len;
        while (l < h) {
            int mid = l + (h - l) / 2;
            if (tails[mid] == key) {
                return mid;
            } else if (tails[mid] > key) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        LIS test = new LIS();
        System.out.println(test.lengthOfLIS2(nums));
    }

}
