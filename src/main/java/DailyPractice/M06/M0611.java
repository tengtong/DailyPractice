package DailyPractice.M06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// 279. 完全平方数
// 用完全平方数来分割整数
// 返回和为n的最少完全平方数量
public class M0611 {

    // 方法一：生成 <=n 的所有完全平方数，然后使用可重复子集来凑完全平方数
    //       贪心先用最大的完全平方数去填，然后剩余的再依次去找
    //       利用动态规划，对每个重复的数的结果保留，保留子解的结果
    // 思路：暴力枚举、递归、重复计算、哈希表
    public int numSquares(int n) {
        // 截止条件
        if (n == 1) {
            return 1;
        }
        // 首先任何都可以转换成完全平方数累加和，因为最小的完全平方数是1
        List<Integer> squareList = generateSquareList(n);
        int min = Integer.MAX_VALUE;
        // 对squaerList进行倒序排序
        squareList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 利用贪心算法先用最大值匹配，再对差值进行递归计算
        for (int num : squareList) {
            min = Math.min(min, numSquares(n - num) + 1);
        }
        return min;
    }

    public int numSquares1(int n) {
        List<Integer> squareList = generateSquareList(n);
        int[] dp = new int[n + 1];
        // 目标数为n，依次对子集进行计算，以求出最后的结果
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            // 找出每个i的对应最小值min，计为dp[i]
            for (int square : squareList) {
                if (square > i) {
                    break;
                }
                min = Math.min(min, dp[i - square] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }
    // 找出n中最大完全平方数
    public List generateSquareList(int n) {
        List list = new LinkedList();
        for (int i = 1; i * i <= n; i++) {
            list.add(i * i);
        }
        return list;
    }
    private List<Integer> generateSquareList1(int n) {
        List<Integer> squareList = new ArrayList<>();
        int diff = 3;
        int square = 1;
        while (square <= n) {
            squareList.add(square);
            square += diff;
            diff += 2;
        }
        return squareList;
    }

    public static void main(String[] args) {
        M0611 test = new M0611();
        System.out.println(test.numSquares(12));
    }
}
