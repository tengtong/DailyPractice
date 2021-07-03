package dailyPractice.M06;

import java.util.Arrays;

// 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
public class M0601 {

    // 方法一：模拟法
    // 数值较大时，要用long类型统计，int数据范围有限
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        // 针对每个qurie进行ans
        boolean[] answer = new boolean[queries.length];
        // sums[i]表示包含candiesCount[i]的candiesCount数组前i项元素的累计和
        long[] sums = new long[candiesCount.length];
        sums[0] = candiesCount[0];
        for (int i = 1; i < candiesCount.length; i++) {
            sums[i] = sums[i - 1] + candiesCount[i];
        }
        // 求解ans
        for (int i = 0; i < answer.length; i++) {
            int type = queries[i][0];
            int day = queries[i][1];
            int cap = queries[i][2];
            long maxCapSum = (long) (day + 1) * cap;
            long minCapSum = (long) (day + 1) * 1;
            if (type == 0) {
                answer[i] = minCapSum <= sums[type];
            } else {
                answer[i] = maxCapSum > sums[type - 1   ] && minCapSum <= sums[type] ;
            }
        }
        return answer;
    }

    // 方法二：模拟法
    // 对判断进行了优化，思路更清晰，取两个区间的交集
    public boolean[] canEat1(int[] candiesCount, int[][] queries) {
        // 针对每个qurie进行ans
        boolean[] answer = new boolean[queries.length];
        // sums[i]表示包含candiesCount[i]的candiesCount数组前i项元素的累计和
        long[] sums = new long[candiesCount.length];
        sums[0] = candiesCount[0];
        for (int i = 1; i < candiesCount.length; i++) {
            sums[i] = sums[i - 1] + candiesCount[i];
        }
        // 求解ans
        for (int i = 0; i < answer.length; i++) {
            int type = queries[i][0];
            int day = queries[i][1];
            int cap = queries[i][2];
            // 区间1：[minSum, maxSum]
            // 根据cap的区间，实际在day+1天内能吃到糖果的数量
            long minCapSum = day + 1;
            long maxCapSum = (long) (day + 1) * cap;
            // 区间2：[typeMinSum, typeMaxSum]
            // 要保证能吃到type类型的糖果，数量前缀和，sum[type-1]、sum[type]
            long typeMinSum = type == 0 ? 1 : sums[type - 1] + 1;
            long typeMaxSum = sums[type];
            // 只要保证两个区间有交集就可以了
            answer[i] = !(minCapSum > typeMaxSum || maxCapSum < typeMinSum);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] candiesCount = {7,11,5,3,8};
        int[][] queries = {{2,2,6}};
        M0601 test = new M0601();
        boolean[] ans = test.canEat(candiesCount, queries);
        System.out.println(Arrays.toString(ans));
    }
}
