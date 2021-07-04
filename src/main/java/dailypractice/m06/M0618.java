package dailypractice.m06;

// 483. 最小好进制
public class M0618 {

    // 方法一：二分查找
    // 思路：因为最终的答案在一个区间内，[3,n]，故可以对这个区间进行二分查找来寻找答案
    //      同时这里还有一个信息，进制数越小，整个数列的长度就长。想要寻找尽可能获取最小的进制，就要尽量取最长的长度
    //      且，当进制数为3时，对当前数n来数，也有一个最长的长度；对进制n来说，长度就是2，所以长度也是可以有区间的，即[2（n进制的长度）,3进制的长度]
    //      当然，题目要求每个位上全为1，所以可以进行计算
    //      故在进制数k和位数m的区间都确定的情况，可以二分来求得答案
    public String smallestGoodBase(String n) {
        return "";
    }

    // 方法二：leetcode
    public String smallestGoodBase1(String n) {
        for (int i = 62; i > 2; i--) {
            long left = 2, right = (long)Math.pow(Long.parseLong(n), 1.0 / (i - 1));
            while (left <= right) {
                long mid = (right - left) / 2 + left;
                long ans = check(mid,i);
                if (ans > Long.parseLong(n)) {
                    right = mid - 1;
                } else if (ans < Long.parseLong(n)) {
                    left = mid + 1;
                } else {
                    return Long.toString(mid);
                }
            }
        }
        return Long.toString(Long.parseLong(n)-1);
    }
    private long check(long k, long len) {
        long ans = 0, pow = 1;
        while (len-- > 0) {
            ans += pow;
            pow *= k;
        }
        return ans;
    }
}
