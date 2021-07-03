package DailyPractice.M05;

import java.util.HashMap;
import java.util.Map;

// 13. 罗马数字转整数
// 思路：与昨天一样，先匹配最大值
// I   1
// V   5
// X   10
// L   50
// C   100
// D   500
// M   1000
public class M0515 {

    // 方法一：利用整数转罗马数字的思路，先列出所有的可能，然后也从大到小进行匹配
    public int romanToInt(String s) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int ret = 0;
        for (int i = 0; i < symbols.length; i++) {
            while (s.startsWith(symbols[i])) {
                ret += values[i];
                s = s.substring(symbols[i].length());
            }
        }
        return ret;
    }

    // 方法二：罗马数字的特性，例如VII，正常大的数字都是在小的数字左边，只要逐个匹配字符，然后进行整数累加即可
    //       但是，也有特例例如XCIX，如果右侧的字符数值比较大，则用右侧减去左侧的数值
    public int romanToInt1(String s) {
        // 用两个数组index相同可以实现，用一个map来存储也可以
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
           put('M', 1000);
           put('D', 500);
           put('C', 100);
           put('L', 50);
           put('X', 10);
           put('V', 5);
           put('I', 1);
        }};
//        int[] values = {1000, 500, 100, 50, 10, 5, 1};
//        char[] symbols = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            // 这里只需要考虑前后字符数值大小的关系，不用考虑出现次数等问题，故是匹配s不是匹配symbols
            // 因为这里不知道s.charAt(i)在symbols中的具体位置，就是数组不能实现O(1)查找效率，所以用map来存储
            if (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                ret -= map.get(s.charAt(i));
            } else {
                ret += map.get(s.charAt(i));
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String s = "XCIX";
        M0515 test = new M0515();
        System.out.println(test.romanToInt1(s));
    }
}
