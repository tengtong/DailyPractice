package dailyPractice.M05;

// 1035. 不相交的线
public class M0521 {

    // 思路一：最长公共子序列...
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        // dp[i][j]：str1从(0-i)和str2从(0-j)的最长公共子序列长度
        // nums1[i] == nums2[j], dp[i][j] = Math.max(dp[i-1][j-1] + 1,  Math.max(dp[i-1][j], dp[i][j-1]))
        // mums1[i] != nums2[j], dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = 0;
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                // dp[i][j]默认是dp[i][j-1]、dp[i-1][j]的较大值，在i、j不相同的情况
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                // 当nums1[i-1]==nums2[j-1]相同时，第i和j位都占了，那么肯定要比较dp[i-1][j-1]+1
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }
        return dp[len1][len2];
    }

    // 思路二：找出所有相同数字在两个数组中的位置i，j，形成[i,j]，找出所有的不相交的区间
    //      思路失败
    public int maxUncrossedLines1(int[] nums1, int[] nums2) {
        return 0;
    }
}
