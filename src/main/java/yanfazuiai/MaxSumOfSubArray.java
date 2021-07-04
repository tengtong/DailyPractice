package yanfazuiai;

//子数组的最大累加和
//思路：最值？dp？
public class MaxSumOfSubArray {

    // 方法一
    // dp[i]：表示以nums[i]为结尾的的连续子数组的最大和
    public int maxsumofSubarray (int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        // dp[i]仅仅表示以nums[i]为结尾的的连续子数组的最大和，dp[arr.length - 1]不一定是最大的累加和
        //   故在需要统计每个dp[i]来找出最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + arr[i] : arr[i];
            // 求较大值，也可以用Math.max函数
            max = max > dp[i] ? max : dp[i];
        }
        return max;
    }

    // 方法二：评论中带注释的答案
    public int maxsumofSubarray1 (int[] arr) {
        //1.dp状态设置： dp[i]就是以nums[i]为结尾的的连续子数组的最大和
        int[] dp = new int[arr.length];
        //2.初态设置：dp[0]必须包含nums[0] 所以：
        dp[0] = arr[0];
        int res = dp[0];
        //3.转移方程：
        //因为nums[i]无论如何都要包括 那么可以选择的余地就是dp[i-1]了
        //dp[i-1]<0 那么不如只选nums[i]了 dp[i-1]>0 那就nums[i]带上dp[i-1]
        for (int i = 1; i < arr.length; i++) {
            dp[i] =  dp[i-1] > 0 ? dp[i-1] + arr[i] : arr[i];
            //由于无论如何都要包括nums[i] 那么如果最后的nums[len-1]<0
            //那么此时dp[len-1]肯定不是最大连续和 需要max筛选
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
