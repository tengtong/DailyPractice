package dailypractice.m07;

import java.util.Arrays;

/**
 * 274. H 指数
 * 一共n篇论文，共有h篇论文至少在引用次数在h及以上
 * @author tengtong
 */
public class M0711 {
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
            int mid = begin + (end - begin) / 2 + 1;
            boolean isH = isHIndex(citations, mid);
            if (isH == true) {
                begin = mid + 1;
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

    /**
     * 方法二：排序
     * @param citations
     * @return
     */
    public int hIndex1(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        // 倒序遍历，当前指数为h，h已经是符合条件的值
        // 如果当前篇论文的值比h大，说明至少多存在一篇论文，h+1
        // 倒序是为了保证h能有效
        // 总结：不推荐排序，用二分更加直观
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    public static void main(String[] args) {
        int[] citations = {1,2};
        M0711 test = new M0711();
        System.out.println(test.hIndex(citations));
    }
}
