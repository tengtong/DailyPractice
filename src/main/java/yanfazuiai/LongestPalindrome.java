package yanfazuiai;

// 5. 最长回文子串
// 思路：最值问题，动态规划
public class LongestPalindrome {

    // 方法一：逐个元素判断过去，中心扩展法
    public String longestPalindrome(String s) {
        // 因为要返回最长的回文子串，所以要记录子串的start和end，用于substring
        // 这里的end是包含在内的
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 对称中心为一个元素
            int len1 = helper(s, i, i);
            // 对称中心为两个元素
            int len2 = helper(s, i, i + 1);
            int len = len1 > len2 ? len1 : len2;
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + (len / 2);
            }
        }
        return s.substring(start, end + 1);
    }
    // 方法：求在s内，以i和j为中心的最长回文子串
    public int helper(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }

    // 方法二：最值问题，动态规划
    // 大问题：s中最长的回文子串
    // 子问题：s的子串
    // dp[i][j]：判断i～j是否是回文串，这里的状态方程比较特殊
    // 一次只需要判断s.charAt(i)==s.charAt(j)，剩余的dp[i+1][j-1]已经判断过了
    public String longestPalindrome1(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        // 首先保证dp[i][i]都是1，即自身长度字符
        for (int i= 0; i < len; i++) {
            dp[i][i] = true;
        }
        // 通过maxLen和begin来记录具体的最长子串
        int maxLen = 1;
        int begin = 0;
        // 以长度为判断逻辑，将所有的子串长度都考虑进去，类似滑动窗口
        // 长度从2开始，长度为1的已经在上面都考虑过了
        for (int curLen = 2; curLen <= len; curLen++) {
            // 列举所有的左边界情况
            for (int i = 0; i < len; i++) {
                // 由长度和左边界可以得到右边界情况
                int j = i + curLen - 1;
                // 如果右边界index超过数组最大值，则不考虑情况
                if (j >= len) {
                    break;
                }
                // 如果不超过，则进行正常的逻辑判断
                if (s.charAt(i) == s.charAt(j)) {
                    // 首先要判断一下数组长度是否小于等于2，是则直接为true
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    // 如果不相等，则置false
                    dp[i][j] = false;
                }
                // 如果dp[i][j]为true，则表示当前i-j为回文子串，则需要统计出最大的子串
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        String s = "12abccba";
        LongestPalindrome test = new LongestPalindrome();
        String ret = test.longestPalindrome(s);
        System.out.println(ret);
    }
}
