package dailyPractice.M05;

// 461. 汉明距离
// 找出两个数字二进制不同的个数
public class M0527 {

    // 方法一：二进制逐位比较法
    public int hammingDistance(int x, int y) {
        int count = 0;
        // 遍历二进制数字，用while，x>>1，类似链表的遍历思想
        while (x > 0 || y > 0) {
            int xx = x & 1;
            int yy = y & 1;
            if (xx != yy) {
                count++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        return count;
    }

    // 方法二：异或
    //       相同为0，不同为1，统计异或结果的1的个数
    public int hammingDistance1(int x, int y) {
        int xXorY = x ^ y;
        int count = 0;
        while (xXorY > 0) {
            count += xXorY & 1;
            xXorY = xXorY >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        M0527 test = new M0527();
        System.out.println(test.hammingDistance(1, 4));
    }
}
