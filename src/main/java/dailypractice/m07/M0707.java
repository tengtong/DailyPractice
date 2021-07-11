package dailypractice.m07;

import java.util.*;

/**
 * 1711. 大餐计数
 * @author tengtong
 */
public class M0707 {
    /**
     * 方法一：hashMap
     * 找出数组中任意两个数（无顺序），使其之和为2的幂次方，求一共有几种组合
     * @param deliciousness
     * @return
     */
    public int countPairs(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = deliciousness.length;
        for (int i = 0; i < n; i++) {
            int val = deliciousness[i];
            for (int sum = 1; sum <= maxSum; sum = sum * 2) {
                int count = map.getOrDefault(sum - val, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return pairs;
    }

    public static void main(String[] args) {
        int[] deliciousness = {1,3,5,7,9};
        M0707 test = new M0707();
        System.out.println(test.countPairs(deliciousness));
    }
}
