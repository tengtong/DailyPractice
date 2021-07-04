package dailypractice.m05;

//7、整数反转
//题目要求：假设环境不允许存储 64 位整数，即long类型
//思路：整数翻转还是用 % /10 *10来实现会比转化成字符串方便的多，建议使用方法二
public class M0503 {

    //方法一：整数转成字符串翻转（long类型不允许，解法失效）
    //      不推荐，因为翻转前后要考虑的边界问题
    public int reverse(int x) {
        if (x < - Math.pow(2, 31) || x > Math.pow(2, 31) - 1 || x == - Math.pow(2, 31)) {
            return 0;
        }
        long retLong;
        StringBuilder sb;
        if (x < 0) {
            sb = new StringBuilder(String.valueOf(-x));
            retLong = -Long.valueOf(sb.reverse().toString());
        } else {
            sb = new StringBuilder(String.valueOf(x));
            retLong = Long.valueOf(sb.reverse().toString());
        }
        if (retLong < - Math.pow(2, 31) || retLong > Math.pow(2, 31) + 1) {
            return 0;
        }
        return (int) retLong;
    }

    //方法二：用 取余% 除/10 和 乘*10 实现翻转
    //      -213 % 10 == -3，故不用特意考虑正负数的问题
    public int reverse1(int x) {
        int ret = 0;
        int yuShu = 0;
        while (x != 0) {
            //!!!注意：这里不能用 ret*10来判断，而是要用 Integer.MIN_VALUE/10来判断
            //        因为如果*10之后超过int类型的范围，结果就会发生错误，被截取掉了
            if (ret < Integer.MIN_VALUE || ret > Integer.MAX_VALUE
                    || ret < Integer.MIN_VALUE / 10 || ret > Integer.MAX_VALUE / 10) {
                return 0;
            }
            yuShu = x % 10;
            x /= 10;// 3/10 = 0；
            ret = ret * 10 + yuShu;
        }
        return ret;
    }

    //方法三：辅助栈翻转
    //      但是字符串可以用栈来相互拼接，整数还是建议使用 % /10 *10
    public int reverse2(int x) {
        return 0;
    }

    public static void main(String[] args) {
        M0503 test = new M0503();
        System.out.println(test.reverse1(1534236469));
    }
}
