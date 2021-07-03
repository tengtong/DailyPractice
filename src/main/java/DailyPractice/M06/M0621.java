package DailyPractice.M06;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

// 401. 二进制手表
// 小时不补0，分钟补0
public class M0621 {
    // 思路：输入总的亮灯数，输出所有可能的时间
    //      列举所有可能，可以用回溯的方法
    private static final int[] hours = {1, 2, 4, 8};
    private static final int[] minutes = {1, 2, 4, 8, 16, 32};
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> combinations = new ArrayList<>();
        if (turnedOn == 0) {
            return combinations;
        }
        doCombination(new Pair<>(0, 0), combinations, 0, turnedOn);
        return combinations;
    }
    private void doCombination(Pair<Integer, Integer> time, List<String> combinations, int curCount, int turnedOn) {
        if (curCount == turnedOn) {
            String hour, min;
            hour = time.getKey().toString();
            if (time.getValue() / 10 == 0) {
                min = "0" + time.getValue().toString();
            } else {
                min = time.getValue().toString();
            }
            String curTime = hour + ":" + min;
            combinations.add(curTime);
            return;
        }
//        int curDigits = digits.charAt(prefix.length()) - '0';
//        String letters = KEYS[curDigits];
//        for (char c : letters.toCharArray()) {
//            prefix.append(c);                         // 添加
//            doCombination(prefix, combinations, digits);
//            prefix.deleteCharAt(prefix.length() - 1); // 删除
//        }
    }

    // 方法二：枚举时间，然后取匹配亮灯数
    public List<String> readBinaryWatch1(int turnedOn) {
        List<String> ans = new ArrayList<String>();
        for (int i = 0; i < 1024; ++i) {
            int h = i >> 6, m = i & 63; // 用位运算取出高 4 位和低 6 位
            if (h < 12 && m < 60 && Integer.bitCount(i) == turnedOn) {
                ans.add(h + ":" + (m < 10 ? "0" : "") + m);
            }
        }
        return ans;
    }






    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return combinations;
        }
        // combinations结果集，digits目标数
        // 这里不同于string的字符串常亮池，一般的饮用地址，就直接传"指针"进去就可以，数据不是局部变量
        doCombination1(new StringBuilder(), combinations, digits);
        return combinations;
    }

    private void doCombination1(StringBuilder prefix, List<String> combinations, final String digits) {
        // 如果前缀字符串长度和总的相同，表示都处理完了，直接将当前结果存入即可
        if (prefix.length() == digits.length()) {
            combinations.add(prefix.toString());
            return;
        }
        // 初始前缀长度为0，这里取第一个数字
        int curDigits = digits.charAt(prefix.length()) - '0';
        // 从final数组中取出当前数字的所有可能，即"abc"
        String letters = KEYS[curDigits];
        // 然后依次枚举，'a'、''b'、'c'
        for (char c : letters.toCharArray()) {
            prefix.append(c);                         // 添加
            // 将当前结果存入到前缀结果中，然后依次对后续结果进行相同的操作，直至枚举完毕
            doCombination1(prefix, combinations, digits);
            prefix.deleteCharAt(prefix.length() - 1); // 删除
        }
    }
}
