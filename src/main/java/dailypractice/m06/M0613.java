package dailypractice.m06;

// 278. 第一个错误的版本
// 可以调用bool isBadVersion(version)接口来判断当前版本是否是错误版本，输入n为总版本的个数
public class M0613 {

    // 方法一：二分查找
    // 找到第一个输出为true
    public int firstBadVersion(int n) {
        int i = 1;
        int j = n;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (isBadVersion(mid) == false) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i;
    }
    private boolean isBadVersion(int version) {
        return true;
    }
}
