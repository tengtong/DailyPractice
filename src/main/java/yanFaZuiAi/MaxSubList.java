package yanFaZuiAi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 最大不重复子串
// 思路：用一个滑动窗口去锁定这个不重复子串的区间，如果遇到重复元素，则用前一个重复元素的下标+1 再继续往后搜索
// 实现：1、双指针来确定区间，更新start、end位置
//      2、用一个map来记录元素出现的index，如果有冲突就更新value，map<arr[i], Index>
public class MaxSubList {

    // 最大不重复子数组
    public int maxLength (int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;
        int curLen = 0;
        for (int start = 0, end = 0; end < arr.length; end++) {
            // 如果出现重复元素，则从前一个重复元素的下标+1开始往后继续遍历
            if (map.containsKey(arr[end])) {
                // 这里要注意，如果出现多个重复元素，那么start要取较大的位置，不然后面还会出现重复元素
                // 比如1，2，3，4，3，5，1。到第二个3时，以后的子串起点start为4，
                // 到第二个1时，如果不取最大的start，按start = map.get(arr[end]) + 1
                // 算出起点start为2，显然以起点start=2，结尾end=1的子串234351有重复的，
                // 因此start要取最大的
                start = Math.max(start ,map.get(arr[end]) + 1);
            }
            curLen = end - start + 1;
            maxLen = maxLen > curLen ? maxLen : curLen;
            // 无论是否出现重复，都更新value
            map.put(arr[end], end);
        }
        return maxLen;
    }

    // 最大不重复子串
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int curLen = 0;
        for (int start = 0, end = 0; end < chars.length; end++) {
            // 如果出现重复元素，则从前一个重复元素的下标+1开始往后继续遍历
            if (map.containsKey(chars[end])) {
                // 这里要注意，如果出现多个重复元素，那么start要取较大的位置，不然后面还会出现重复元素
                start = Math.max(start ,map.get(chars[end]) + 1);
            }
            curLen = end - start + 1;
            maxLen = maxLen > curLen ? maxLen : curLen;
            // 无论是否出现重复，都更新value
            map.put(chars[end], end);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {2,2,3,4,3,5};
        MaxSubList test = new MaxSubList();
        System.out.println(test.maxLength(arr));
    }
}
