package dailyPractice.M05;

import java.util.Arrays;

// 1482. 制作 m 束花所需的最少天数
// 思路：前提，如果m*k<bloomDay，那么是一定可以制作出花束的
//      一定可以制作出花束，而bloomDay中记录这所有花的开花时间，存在最小天数和最大天数
//      那么满足条件的最少的天数就一定存在这个范围内，就可以是用二分查找来寻找满足条件的最小值
public class M0509 {

    public int minDays(int[] bloomDay, int m, int k) {
        // 花园盛开花的总数
        int bloomTotal = bloomDay.length;
        // 一共需要的花的总数
        int mk = m * k;
        if (mk > bloomTotal) {
            return -1;
        }
        // !!!如果mk <= bloomTotal，则一定可以制作出花束
        // 使用二分法找到满足条件的最少天数
        int low = Arrays.stream(bloomDay).min().getAsInt();
        int high = Arrays.stream(bloomDay).max().getAsInt();
//      int sum = Arrays.stream(bloomDay).sum();
        // 二分查找只要对当前的mid进行考虑就可以了，一次循环体只需要考虑一个mid就可以
        // 下一次的 mid 不是简单取 mid - 1，而是通过 mid=(low+high)/2 计算出来的
        // 以下写法完全错误
        // if (isHuaShuByDayM(bloomDay, m, k, mid) == true &&
        //      isHuaShuByDayM(bloomDay, m, k, mid - 1) == false)
        while (low < high) {
            // 二分查找mid要放在 while 里面，不然没法每次更新mid
            int mid = low + (high - low) / 2;
            // 这种写法是找到符合条件最左侧的 mid，即最小天数
            if (isHuaShuByDayM(bloomDay, m, k, mid)) {
                 high = mid;
            } else {
                // 这 else 表示 isHuaShuByDayM(bloomDay, m, k, mid)==false 的情况
                // 那么自然要从 mid+1 开始
                low = mid + 1;
            }
        }
        return low;
    }

    // 方法：判断在指定天数M内，盛开的花能否制作指定的花束
    //      判断bloomDay数组内，最大值<=dayM，长度为k的不重复子数组个数是否小于m个
    // 实现1：连续子数组不需要用双指针，会把问题弄复杂，只需要判断当前元素的是否满足条件即可
    public boolean isHuaShuByDayM(int[] bloomDay, int m, int k, int dayM) {
        int bouquets = 0;
        int flowers = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= dayM) {
                flowers++;
                // 如果加上当前位置的花，已经足够凑成一束，则累计加1，开始组装下一束花
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        if (bouquets >= m) {
            return true;
        } else {
            return false;
        }
    }

    // 方法：判断在指定天数M内，盛开的花能否制作指定的花束
    //      判断bloomDay数组内，最大值<=dayM，长度为k的不重复子数组个数是否小于m个
    // 实现2：滑动窗口，在bloomDay数组内判断，是否存在m个长度为k，且最大值小于dayM的不重复子数组
    //       滑动窗口较复杂，不推荐，直接使用方法1进行判断更直观
    public boolean isHuaShuByDayM1(int[] bloomDay, int m, int k, int dayM) {
        int count = 0;
        int start = 0;
        int end = 0;
        int curLen;
        while (end < bloomDay.length) {
            curLen = end - start + 1;
            if (bloomDay[end] <= dayM && curLen < k) {
                end++;
            } else if (bloomDay[end] <= dayM && curLen == k) {
                count++;
                end++;
                // 区间重新开始统计，从 end+1 开始
                start = end;
            } else {
                end++;
                start = end;
            }
        }
        if (count >= m) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] arr = {7,7,7,7,12,7,7};
        M0509 test = new M0509();
        System.out.println(test.minDays(arr, 2, 3));
    }
}
