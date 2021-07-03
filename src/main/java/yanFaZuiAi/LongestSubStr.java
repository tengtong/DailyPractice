package yanFaZuiAi;

import java.util.Arrays;

// 1371. 每个元音包含偶数次的最长子字符串
// 思路：寻找最长子串，对子串只有一个要求，要求子串中的aeiou只出现偶数次
public class LongestSubStr {

    // 方法一：用一个异或结果来记录遍历当前i位置时，aeiou出现次数，为0统计当前子串
    //       但是这需要对子串都进行统计，需要两个for，枚举start、end的位置，复杂度偏高
    public int findTheLongestSubstring(String s) {
        return 0;
    }

    // 方法二：方法一优化，优化了寻找符合条件的子串过程，只需要一遍for循环
    public int findTheLongestSubstring1(String s) {
        int n = s.length();
        // aeiou每个是否奇偶，共有32个状态
        // 根据题意aeiou每个出现奇偶数共有32种状态，当这32种状态中任一状态出现第二次，就是我们需要统计的时候
        // 故pos数组记录的是每一状态出现时，当前的遍历的位置 i
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // 这里使用异或来辅助，是因为出现一次为1，出现第二次就抵消了为0
            // 而且对具体出现多少次没有要求，只要求是偶数即可
            if (ch == 'a') {
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            // 如果子串 [0，i] 与字串 [0,j] 状态相同，那么字串 [i+1,j] 的状态一定是 0
            // 因为在遍历 i 时，通过异或操作已经在state中记录了[0, i]的aeiou的出现次数，即奇数或偶数
            // 而在遍历到 j 时，中间如果又出现了aeiou，会再次进行异或，将奇数变成偶数，或者偶数变成奇数
            // 故遍历到到 j 时，如果状态和遍历到 i 时的状态相同，则表示从[i+1, j]中间又出了偶数次（包括0次）的aeiou
            // 那么中间的这段子串[i+1, j]，就是符合题目要求的子串，所以只要不断更新符合条件的子串长度，就能找出符合条件的最长子串长度
            // 即根据题意aeiou每个出现奇偶数共有32种状态，当这32种状态中任一状态出现第二次，就是我们需要统计的时候
            if (pos[status] >= 0) {
                ans = Math.max(ans, i - pos[status] + 1);
            } else {
                // 如果相同状态还没出现过，则pos[statu]的初始值为1，则将当前坐标记录进去，当作子串的起始值
                pos[status] = i + 1;
            }
        }
        return ans;
    }
}