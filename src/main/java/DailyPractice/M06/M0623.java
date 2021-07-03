package DailyPractice.M06;

import com.sun.javaws.IconUtil;

// 剑指 Offer 15. 二进制中1的个数
public class M0623 {

    // 方法一：二进制逐位判断
    // 思路：这题处理的是无符号整数
    // int 有符号取值范围 [-2^31, 2^31-1]
    // int 无符号取值范围 [0, 2^32-1]
    // 1左移i位，n不移动
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            // % 与运算符号，计算结果为两个数的位与结果，不是'=='
            // 如果当前位上有数存在，则与结果不为0，11100 & 00100 == 00100 == 4
            // 和1与运算的结果只有0和1，故可以拿来 count+=(n&1)
           if ((n & (1 << i)) != 0) {
               count++;
           }
        }
        return count;
    }
    // 方法二：逐位计算法，n右移，n&1
    public int hammingWeight1(int n) {
        int ans = 0;
        // 因为这里是无符号计算，故无符号数字n可能上来就被认为是有符号中的负数，故while(n!=0)
        while (n != 0) {
            ans += (n & 1);
            // 无符号右移
            n >>>= 1;
        }
        return ans;
    }

    // 方法三：lowbit
    public int hammingWeight2(int n) {
        int ans = 0;
        for (int i = n; i != 0; i -= lowbit(i)) ans++;
        return ans;
    }
    int lowbit(int x) {
        return x & -x;
    }

    // 方法四：分组统计
    public int hammingWeight3(int n) {
        n = (n & 0x55555555) + ((n >>> 1)  & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2)  & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4)  & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8)  & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }

    // 方法五：biteCount
    public int hammingWeight4(int n) {
        int res = Integer.bitCount(n);
        return res;
    }

    public static void main(String[] args) {
        M0623 test = new M0623();
//        System.out.println(test.hammingWeight());
    }
}
