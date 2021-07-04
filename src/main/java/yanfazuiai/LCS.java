package yanfazuiai;

// 最长公共子串
// 说明：最长公共子串 == 最长公共连续子序列
public class LCS {

    // 最长公共子序列（不连续，相对顺序不变）
    // 动态规划
    public int lCSubsequence(String text1, String text2) {
        // 这里类似链表的哑节点方式，可以不用再对头部元素进行单独处理
        text1 = " " + text1;
        text2 = " " + text2;
        int text1L = text1.length();
        int text2L = text2.length();
        // dp[i][j]：str1从(0-i)和str2从(0-j)的最长公共子序列长度
        // 如果结果为具体子序列，可以用一个map<length, String>来存储结果
        int[][] dp = new int[text1L][text2L];
        // dp数组初始化
        // 因为后面i和j都会从1开始，故初始为0和1都可以，不会再重复计算
        for (int i = 0; i < text1L; i++) dp[i][0] = 0;
        for (int j = 0; j < text2L; j++) dp[0][j] = 0;
        // dp计算
        for (int i = 1; i < text1L; i++) {
            for (int j = 1; j < text2L; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    // i，j初始从1开始，就不用对头部元素进行单独处理
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1L - 1][text2L - 1];
    }

    // 最长公共子串（连续子序列）
    // 最长公共子序列的解法，最长共子串也能用，直接复用一套代码
    public String LCSubString1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals(" ") || str2.equals(" ")) {
            return "-1";
        }
        //动态规划  二维数组，斜下来就是最长字串
        int str1len = str1.length();
        int str2len = str2.length();
        // 最终的子串，只需要知道子串的结束位置index，和子串长度maxLen，就可以从原数组中截取到子串
        int maxLen = 0;
        int index = 0;
        //定义一个二维数组
        int[][] dp = new int[str1len][str2len];
        for (int i = 0; i < str1len; i++) {
            for (int j = 0; j < str2len; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    // 这里没有使用"哑节点"的方法，需要对头部元素进行单独处理
                    if (j == 0 || i == 0) {
                        dp[i][j] = 1;
                        if(maxLen == 0) {
                            maxLen = 1;
                            index = i;
                        }
                    }  else{
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        if(maxLen <= dp[i][j]){
                            maxLen = dp[i][j];
                            index = i;
                        }
                    }
                }
            }
        }
        if(maxLen == 0){
            return "-1";
        }
        // substring方法，左闭右开
        // 这里因为最后的位置要包括index本身位置的元素，所以endIndex==index+1，beginIndex也是同理
        // String s = "abcde";
        // int maxLen = 2;
        // int index = 3;
        // s.substring(index + 1 - maxLen, index + 1) == "bc";
        return str1.substring(index + 1 - maxLen, index + 1);
    }
}
