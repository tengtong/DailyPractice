package yanFaZuiAi;

//121、股票交易（一次交易）
public class MaxProfit {

    //方法一：访问以prices[i]结尾的数组，求出最大利润，并找到i-1天内的最小价格
    public int maxProfit(int[] prices) {
        //前i-1天的最大收益
        // int preMax = 0;
        //当天的最大收益
        int curMax = 0;
        //前i天内的最小价格
        int minPrice = prices[0];
        int n = prices.length;
        for(int i = 1; i < n; i++){
            curMax = Math.max(curMax, prices[i] - minPrice);
            //minPrice：第i-1天内买入的最小值
            minPrice = Math.min(minPrice, prices[i]);
        }
        return curMax;
    }

    //方法二：以当前价格减去前一天的价格，构建一个利润数组profit[]，遍历利润数组最大子数组的和
    public int maxProfit1(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] profit = new int[prices.length];
        profit[0] = 0;
        for (int i = 1; i < profit.length; i++) {
            profit[i] = prices[i] - prices[i - 1];
        }
        int[] dp = new int[profit.length];
        dp[0] = profit[0];
        int max = profit[0];
        for (int i = 1; i < profit.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + profit[i] : profit[i];
            max = max > dp[i] ? max : dp[i];
        }
        return max;
    }
}
