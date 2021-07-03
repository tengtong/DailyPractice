package DailyPractice.M05;

import java.util.Arrays;

// 1310. 子数组异或查询
public class M0512 {

    // 方法一：前缀异或法，DE = ABC ^ ABCDE，先求出所有以arr[i]为结尾的异或结果数组xor[i]
    // 所有的区间结果可以转化成大xor[i]和小xor[i]的异或结果
    // 也是动态规划数组的相同使用方法，不过这题不属于动态规划，因为没有利用子问题来求解大问题，只不过使用了中间状态而已
    public int[] xorQueries(int[] arr, int[][] queries) {
        // xor[i]表示以arr[i]为结尾的异或结果
        int[] xor = new int[arr.length];
        xor[0] = arr[0];
        // 先用一个for循环计算出中间结果，从而将空间复杂度从O(n*n)降到O(n)
        for (int i = 1; i < arr.length; i++) {
            xor[i] = arr[i] ^ xor[i - 1];
        }
        int[] ans = new int[queries.length];
        int j = 0;
        for (int[] singleArr : queries) {
            int start = singleArr[0];
            int end = singleArr[1];
            if (start == 0) {
                ans[j++] = xor[end];
            } else {
                // [1,3]的结果包括arr[1]，不能把arr[1]也前缀处理掉了
                ans[j++] = xor[start - 1] ^ xor[end];
            }
        }
        return ans;
    }

    // 方法二：暴力解法，容易超时
    public int[] xorQueries1(int[] arr, int[][] queries) {
        int[] ans = new int[queries.length];
        int j = 0;
        for (int[] singleArr : queries) {
            int start = singleArr[0];
            int end = singleArr[1];
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum ^= arr[i];
            }
            ans[j++] = sum;
        }
        return ans;
    }

    public static void main(String[] args) {
        M0512 test = new M0512();
        int[] arr = {1,3,4,8};
        int[][] queries = {{0,1},{1,2},{0,3},{3,3}};
        int[] ret = test.xorQueries(arr, queries);
        System.out.println(Arrays.toString(ret));
    }
}
