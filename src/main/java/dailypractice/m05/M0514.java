package dailypractice.m05;

// 12. 整数转罗马数字
// I  1
// V  5
// X  10
// L  50
// C  100
// D  500
// M  1000
// 1 <= num <= 3999
public class M0514 {

    // 方法一：罗马数字都是先进行最大值匹配
    // 贪心？
    int[] values =     {1000, 900, 500, 400, 100, 90, 50 ,40 ,10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        String ret = "";
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num = num - value;
                ret = ret + symbol;
            }
            if (num == 0) {
                break;
            }
        }
        return ret;
    }

    // 方法二：硬编码
    // 优于方法三，直接将所有结果都列举出来了，然后根据该位置上的数字取对应的roman数字
    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    public String intToRoman1(int num) {
        StringBuffer roman = new StringBuffer();
        // 取num数字各个位置上的数字
        roman.append(thousands[num / 1000]);
        // num%1000 对num取除了千位剩余的数字，再/100取百位上的数字
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }

    // 方法三：暴力法，逐位数字处理,分四种情况处理0-3,4,5-8,9，使用简单的if-else进行处理
    // 数字一共就1-3999，且每个位置上的数0-9都是固定答案，所以不如枚举，例如方法二
    public String intToRoman2(int num) {
        String ret = "";
        int curVal = 0;
        // num / 1000，num除以1000取商
        // num % 1000，num除以1000取余
        int thousand = num / 1000;
        while (thousand-- > 0) {
            ret = ret + "M";
        }
        int hundred = (num % 1000) / 100;
        if (hundred  == 9) {
            ret = ret + "CM";
        } else if (hundred == 4) {
            ret = ret + "CD";
        } else if (hundred >= 5){
            ret = ret + "D";
            while (hundred-- > 5) {
                ret = ret + "C";
            }
        } else {
            while (hundred-- > 0) {
                ret = ret + "C";
            }
        }
        int ten = (num % 100) / 10;
        if (ten  == 9) {
            ret = ret + "XC";
        } else if (ten == 4) {
            ret = ret + "XL";
        } else if (ten >= 5){
            ret = ret + "L";
            while (ten-- > 5) {
                ret = ret + "X";
            }
        } else {
            while (ten-- > 0) {
                ret = ret + "X";
            }
        }
        int one = num % 10;
        if (one  == 9) {
            ret = ret + "IX";
        } else if (one == 4) {
            ret = ret + "IV";
        } else if (one >= 5){
            ret = ret + "V";
            while (one-- > 5) {
                ret = ret + "I";
            }
        } else {
            while (one-- > 0) {
                ret = ret + "I";
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int num = 3499;
        M0514 test = new M0514();
        System.out.println(test.intToRoman(num));
    }
}
