package DailyPractice.M05;

// 664. 奇怪的打印机
// 最少打印次数？最值？动态规划？
public class M0524 {

    // 方法一：逻辑模拟
    // 思路：每次只能打印一种字符，可以对原字符进行覆盖，求打印一个字符串的最少打印次数
    //      字符的种数？好像不对，如果不同字符出现的位置导致，不能连续打印，覆盖成本高
    // 方法不可行
    public int strangePrinter(String s) {
        return 0;
    }

    // 方法二：动态规划
    // 思路：最值问题，动态规划
    //      动态规划，dp[i]和dp[i-1]关系，通过子问的解来推出大问题的解
    //      不需要考虑答大问题的求解过程，只需要关注初始条件
    //      然后找到dp[i]和dp[i-1]的关系，就可以将dp[i]的递推表达式写出来，for执行，自然就得到了最后的结果
    //      还需要多锻炼一下，今天这道是困难题就先算了
    public int strangePrinter1(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i][j - 1];
                } else {
                    int minn = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        minn = Math.min(minn, f[i][k] + f[k + 1][j]);
                    }
                    f[i][j] = minn;
                }
            }
        }
        return f[0][n - 1];
    }
}
