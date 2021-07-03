package DailyPractice.M05;

import java.util.*;

// 1074. 元素和为目标值的子矩阵数量
public class M0529 {

    // 方法一：matrix矩阵中，有许多的个子矩阵，要求出子矩阵的和为target
    //       matrix矩阵中的元素也没有顺序可言，也不能排序，排完序就不是子矩阵了
    //       故需要一个方法对所有的子矩阵进行统计判断
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        return 0;
    }

    // 方法二：前缀和 + 哈希表
    // 思路：这里采用前缀和的方式统计为第i行到第j行的所有子矩阵，并压缩成一维矩阵，然后统计一列中和为k的子数组个数
    //      妙呀
    public int numSubmatrixSumTarget1(int[][] matrix, int target) {
        int ans = 0;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) { // 枚举上边界
            // sum为上边界为i，下边界为j，的所有子矩阵中，列向累加和，统计数组
            // 即列向前缀和数组
            int[] sum = new int[n];
            for (int j = i; j < m; ++j) { // 枚举下边界
                for (int c = 0; c < n; ++c) {
                    // 类似二维dp化简一维dp
                    // {{1,2,2},
                    //  {3,-1,2},
                    //  {4,1,2}}
                    // i=0, j=0, 统计第一行，sum={1,2,2}，然后统计
                    // i=0, j=1, sum为第一行和第二行的列累加和，以此来统计子矩阵，sum={1+3,2+(-1),2+2}
                    sum[c] += matrix[j][c]; // 更新每列的元素和// 当前为第j列，第c个
                }
                ans += subarraySum(sum, target);
            }
        }
        return ans;
    }
    // 方法：判断nums中子数组元素之和为k的个数
    // 思路：前缀和，累加法，减少重复运算
    // nums就是一个矩阵中的所有元素
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        // count记录所有符合结果的个数
        // pre是之前遍历的元素的累加和
        int count = 0, pre = 0;
        for (int x : nums) {
            pre += x;
            // pre是之前所有遍历过的元素的累加和，pre-k，即寻找之前出现的差值
            // 这里的containKey只会在之前遍历过的元素中进行寻找
            if (map.containsKey(pre - k)) {
                // 因为可能存在重复元素，每一个重复元素都是
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] nums = {{1,2,2},{3,-1,2},{4,1,2}};
        M0529 test = new M0529();
        System.out.println(test.numSubmatrixSumTarget1(nums, 4));
    }
}
