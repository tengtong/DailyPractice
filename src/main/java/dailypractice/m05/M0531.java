package dailypractice.m05;

// 342. 4的幂
public class M0531 {

    // 方法一：二进制法
    // 思路：先判断一下是否是2的幂次方，因为2的幂次方好判断
    //      然后再判断一下是否是4的幂次方，即1在奇数位
    public boolean isPowerOfFour(int n) {
        // int类型4个字节，共32位，但是最高位是符号位，0位正，1位负数，即数据表示共31位，即31个位上全是1
        // 但是31个位上，最低位是2^0开始，所以最高位是2^30，即最大值就是2^31-1
        int num = Integer.MAX_VALUE;
        // 故这里的30表示最高位的index为30，是正确的
        for (int i = 1; i <= 30; i = i + 2) {
            num = num - (1 << i);
        }
        // 先判断是否是2的幂次方
        if (n > 0 && (n & (n - 1)) == 0) {
            // 再判断是否是4的幂次方
            if ((n & num) != 0) {
                return true;
            }
        }
        return false;
    }

    // 方法二：在一的基础上，优化4的幂次方判断
    // 方法一优化，4^n % 3 == 1
    public boolean isPowerOfFour1(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }
}
