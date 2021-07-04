package dailypractice.m06;

// 518. 零钱兑换 II
public class M0610 {
    public int change(int amount, int[] coins) {
        // dp[0,1,...,n]
        // 总问题：amount，子问题：所有金额小于amount的金额
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            // 遍历[coin, amount]区间的所有值
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
