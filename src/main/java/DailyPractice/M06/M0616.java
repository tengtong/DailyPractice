package DailyPractice.M06;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// 877. 石子游戏
public class M0616 {

    // 方法一：枚举，每次取都取双向队列头尾中最大的值
    // 思路：不可枚举，这和下棋一样，当前步赢不是赢，要考虑后面几步，取得最终胜利为赢
    //      故不可逐步枚举
    public boolean stoneGame(int[] piles) {
        return false;
    }

    // 方法二：动态规划
    // 思路：博弈型动态规划
    //      一些关于dp[i]的设计的思考
    //      1. 动态规划dp[i]记录的当前状态，并且通过状态之间的关联关系，找到全局最优
    //      2. 记忆性搜索，本质还是搜索，将所以可能都考虑进去了，只不过通过记忆化，剪枝的形式避免重复运算，与动态规划还是有一些小区别
    //      3. dp[i]可以通过当前状态，最后一步，子问题的角度去思考
    public boolean stoneGame1(int[] piles) {
        int length = piles.length;
        // dp[i][j]：表示选取从[i,j]范围内，当前选手和另一个玩家的石子数量之差的最大值
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        // 因为是偶数堆，且是Alex先手，最后一定是Lee为最后一个
        return dp[0][length - 1] > 0;
    }

    // 方法三：数学
    // 思路：一组偶数堆数组可以按照下标可以分为奇、偶数两组
    //      同时，石头的总数是奇数的，所以奇数组和偶数组中一定有一组是较大
    //      然而Alex可以一直选择奇数组或者偶数组，则一定可以选到较大的一组数
    //      所以Alex一定可以获得胜利
    public boolean stoneGame2(int[] piles) {
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {3,7,2,3};
        M0616 test = new M0616();
        System.out.println(test.stoneGame(arr));
    }
}
