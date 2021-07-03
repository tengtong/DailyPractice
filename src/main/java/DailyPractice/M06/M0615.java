package DailyPractice.M06;

import java.util.Arrays;

// 852. 山脉数组的峰顶索引
public class M0615 {
    // 方法一：枚举，遍历一遍数组，对每个数进行判断。。。
    // 时间复杂度O(n)
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    // 方法二：二分查找
    // 找到山脉数组的峰顶
    // [3,4,5,1] => 5,index 2
    // 输入为山脉数组，输出是封顶，故只要找出最数组中的最大值即可，排序再输出时间复杂度O(nlogn)
    // “有序”数组，二分查找
    public int peakIndexInMountainArray1(int[] arr) {
        // 且二分查找的区间是答案区间，故将i=0，j=n-1考虑本就不合理，这里不用考虑边界问题
        int i = 1;
        int j = arr.length - 2;
        int mid;
        while (i <= j) {
            mid = i + (j - i) / 2;
            if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
                i = mid + 1;
            } else if (arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1]) {
                j = mid - 1;
            }
        }
        return -1;
    }

    // 方法三：二分查找优化
    public int peakIndexInMountainArray2(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            // 如果arr[mid] > arr[mid + 1]，那么答案就在[1,mid]之间
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    // 方法四：排序
    // 思路：排序之后下标序号就乱掉了...如果再用hashMap来存储原下标，然后再排序，在原有的时间复杂度上，空间复杂度也需要为O(n)
    // 时间复杂度O(nlogn)
    public int peakIndexInMountainArray3(int[] arr) {
        Arrays.sort(arr);
        return arr[0];
    }
}
