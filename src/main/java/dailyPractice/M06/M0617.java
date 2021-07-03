package dailyPractice.M06;

import java.util.regex.Pattern;

// 65. 有效数字
public class M0617 {

    // 方法一：模拟
    public boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        // 小数情况
        // 整数情况
        // 指数情况
        return true;
    }

    // 方法二：正则匹配
    //       正则表达式匹配字符串
    public boolean isNumber1(String s) {
        String number = "([+-]?(\\d+(\\.\\d*)?|(\\.\\d+)))";
        Pattern base = Pattern.compile(number + "([eE][+-]?\\d+)?");
        return base.matcher(s).matches();
    }

    // 方法三：状态机
    public boolean isNumber2(String s) {
        char[] chars = s.toCharArray();
        // 小数情况
        // 整数情况
        // 指数情况
        return true;
    }
}
