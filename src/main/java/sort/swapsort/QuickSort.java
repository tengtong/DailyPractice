package sort.swapsort;

import java.util.Arrays;

public class QuickSort {
    public void sort(int[] arr, int begin, int end) {
        if (begin >= end || arr == null || arr.length <= 1) {
            return;
        }
        int pivotIndex = partition(arr, begin, end);
        sort(arr, begin, pivotIndex - 1);
        sort(arr, pivotIndex + 1, end);
    }
    public int partition(int[] arr, int begin, int end) {
        // 先获取基准值
        getPivot(arr, begin, end);
        int pivot = arr[end - 1];
        int i = begin;
        int j = end - 1;
        while (i < j) {
            // 先找到左区间中大于pivot的位置
            while (i < j && arr[++i] < pivot) {
            }
            while (i < j && arr[--j] >= pivot) {
            }
            swap(arr, i, j);
        }
        swap(arr, i, end - 1);
        return i;
    }
    // 三元取中法
    public void getPivot(int[] arr, int begin, int end) {
        // 对三个位置进行排序
        int mid = (begin + end) / 2;
        if (arr[end] < arr[mid]) {
            swap(arr, end, mid);
        }
        if (arr[end] < arr[begin]) {
            swap(arr, end, begin);
        }
        if (arr[mid] < arr[begin]) {
            swap(arr, mid, begin);
        }
        // 将中间值放在数组倒数第二个位置
        swap(arr, mid, end - 1);
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,7,3,6,8,2,4,5};
        //1,3,7,6,8,2,4,5
        //1,3,2,6,8,7,4,5
        //1,3,2,4,8,7,6,5
        //1,3,2,4,5,7,6,8//第一遍排序
        //1,3,2,4 | 7,6,8
        //1,3,2 | 4 | 7,6 | 8//当pivot取的不好时，一次partition直接分成1,3,2 | 4两个part，没有进行排序
                             //将复杂度O(nlogn)升级成O(n^2)
        //1,2 | 3 | 4 | 6 | 7 | 8
        //1 | 2 | 3 | 4 | 6 | 7 | 8
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr,0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
