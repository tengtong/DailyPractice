package DailyPractice.M05;

// 231. 判断一个数字是否是2的幂次方
public class M0530 {

    // 方法一：二进制法
    // 思路：2的幂次方，二进制只有一个位上为1，其他都是0
    public boolean isPowerOfTwo(int n) {
        int count = 0;
        // 当n<0，count=0，直接返回false
        while (n > 0) {
            count += n & 1;
            n = n >> 1;
        }
        return count == 1 ? true : false;
    }

    // 方法二：二进制法优化
    // 思路：方法一的优化，不需要逐位判断，如果只有一位为1，则n&(n-1)==0
    public boolean isPowerOfTwo1(int n) {
        // 要对n为负数情况单独考虑
        return n > 0 && (n & (n - 1)) == 0;
    }

    // 方法三：二进制法优化
    // 思路：方法一的优化，不需要逐位判断，如果只有一位为1，则n&(-n)==n，因为负数在二进制中表示，所有位取反后+1
    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & -n) == n;
    }

    // 方法四：
    // 思路：在int类型范围中[-2^31, 2^31-1]，最大就是2^30，即其他所有枚举结果都是2^30的约数，即2^30=2^x * n
    static final int BIG = 1 << 30;
    public boolean isPowerOfTwo3(int n) {
        return n > 0 && (BIG % n == 0);
    }

}
