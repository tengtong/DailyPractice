package dailypractice.m07;

/**
 * 275. H 指数 II
 * @author tengtong
 */
public class M0712 {
    /**
     * 方法一：二分查找
     * h c [1, n]，h有取值范围
     * @param citations 论文的引用次数数组
     * @return
     */
    public int hIndex(int[] citations) {
        if (citations == null || citations.length < 1) {
            return -1;
        }
        // h的取值范围是引用次数和篇数，篇数最少为1，引用次数最少为0，所以h最小取0
        int begin = 0;
        int end = citations.length;
        // 找到最后一个返回true的数字就是h
        while (begin < end) {
            // 因为这里end = mid - 1，是移动的二分，？
            // 可以参考：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
            int mid = (begin + end + 1) / 2;
            boolean isH = isHIndex(citations, mid);
            if (isH == true) {
                begin = mid;
            } else {
                end = mid - 1;
            }
        }
        return begin;
    }
    /**
     * 所有论文中引用次数>=h篇
     * @param citations
     * @param h
     * @return
     */
    public boolean isHIndex(int[] citations, int h) {
        int count = 0;
        for (int i = 0; i < citations.length; i++) {
            int curCitation = citations[i];
            if (curCitation >= h) {
                count++;
            }
        }
        if (count >= h) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] array = {0,1,3,5,6};
        M0712 test = new M0712();
        System.out.println(test.hIndex(array));
    }
}
