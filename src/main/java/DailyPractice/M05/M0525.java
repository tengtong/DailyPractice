package dailyPractice.M05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 1787. 使所有区间的异或结果为零
public class M0525 {

    // 使得nums数组中，区间长度为k的的所有子数组异或结果为0时，对nums数组的最少修改次数
    // 要使得所有长度为k的子数组异或结果为0
    // 异或结果为0，A^A=0，自身异或结果为0
    // 最少修改元素个数，最值，动态规划
    // dp[i][j]：表示[i,j]之间符合条件的最少修改次数
    // 正常逻辑，遍历数组，保证每个k区间的元素异或结果都为0，然后遍历逐个修改？
    public int minChanges(int[] nums, int k) {
        return 0;

    }

    // 方法二：动态规划
    // x 的范围为 [0, 2^10)
    static final int MAXX = 1 << 10;
    // 极大值，为了防止整数溢出选择 INT_MAX / 2
    static final int INFTY = Integer.MAX_VALUE / 2;

    public int minChanges1(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[MAXX];
        Arrays.fill(f, INFTY);
        // 边界条件 f(-1,0)=0
        f[0] = 0;
        for (int i = 0; i < k; ++i) {
            // 第 i 个组的哈希映射
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            int size = 0;
            for (int j = i; j < n; j += k) {
                cnt.put(nums[j], cnt.getOrDefault(nums[j], 0) + 1);
                ++size;
            }
            // 求出 t2
            int t2min = Arrays.stream(f).min().getAsInt();
            int[] g = new int[MAXX];
            Arrays.fill(g, t2min);
            for (int mask = 0; mask < MAXX; ++mask) {
                // t1 则需要枚举 x 才能求出
                for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                    int x = entry.getKey(), countx = entry.getValue();
                    g[mask] = Math.min(g[mask], f[mask ^ x] - countx);
                }
            }
            // 别忘了加上 size
            for (int j = 0; j < MAXX; ++j) {
                g[j] += size;
            }
            f = g;
        }
        return f[0];
    }
}
