package DailyPractice.M06;

// 374. 猜数字大小
// 可以调用int guess(int num) 来获取猜测结果
public class M0614 {
    public int guessNumber(int n) {
        int i = 1;
        int j = n;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (guess(mid) == -1) {
                j = mid - 1;
            } else if (guess(mid) == 1) {
                i = mid + 1;
                // 因为最终i和j都要收束到同一个值即答案，所以while中要i<=j
            } else if (guess(mid) == 0) {
                return mid;
            }
        }
        return -1;
    }
    // -1：猜的数字偏大
    //  1：猜的数字偏小
    //  0：猜对啦
    private int guess(int num) {
        return 1;
    }
}
