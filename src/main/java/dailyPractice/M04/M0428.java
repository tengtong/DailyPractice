package dailyPractice.M04;

import java.util.ArrayList;
import java.util.List;

//633.平方数之和
//题目：给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c
public class M0428 {

    public static void main(String[] args) {
        int c = 4;
        System.out.println(M0428.judgeSquareSum2(c));
    }

    //方法一：暴力法//超时
    public static boolean judgeSquareSum1(int c) {
        boolean flag = false;
        for (int i = 0; i <= c; i++) {
            for (int j = i; j <= c; j++) {
               if (i * i + j * j > c) {
                  break;
               } else if (i * i + j * j == c) {
                   flag = true;
                   return flag;
               }
            }
        }
        return flag;
    }

    //方法二：先求出所有的可能平方数，再求解//超时
    public static boolean judgeSquareSum2(int c) {
        List<Integer> list = M0428.pingFangShu(c);
        for (int i = 0; i < list.size() && list.get(i) <= c; i++) {
            for (int j = i; j < list.size() && list.get(j) + list.get(i) <= c ; j++) {
                if ( list.get(j) + list.get(i) == c) {
                    return true;
                }
            }
        }
        return false;
    }
    private static List<Integer> pingFangShu(int c) {
        ArrayList list = new ArrayList();
        for (int i = 0; i * i <= c; i++) {
            list.add(i * i);
        }
        return list;
    }

    //方法三：sqrt方法
    public static boolean judgeSquareSum3(int c) {
        for (long i = 0; i * i <= c; i++) {
            double temp = Math.sqrt(c - i * i);
            if (temp == (int) temp) {
                return true;
            }
        }
        return false;
    }

    //方法四：双指针
    public static boolean judgeSquareSum4(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);
        while (left <= right) {
            long sum = left * left + right * right;
            if (sum > c) {
                right--;
            } else if (sum < c) {
                left++;
            } else if (sum == c) {
                return true;
            }
        }
        return false;
    }
}
