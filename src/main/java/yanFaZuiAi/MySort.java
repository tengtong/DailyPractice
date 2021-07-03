package yanFaZuiAi;

import java.util.Arrays;

public class MySort {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 将给定数组排序
     * @param arr int整型一维数组 待排序的数组
     * @return int整型一维数组
     */
    public int[] sort (int[] arr) {
        quickSort(arr , 0 , arr.length - 1);
        return arr;
    }
    public void quickSort(int[] arr, int begin, int end) {
        if (begin >= end || arr == null || arr.length <= 1) {
            return;
        }
        int pivotIndex = partition(arr, begin, end);
        quickSort(arr, begin, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, end);
    }
    public int partition(int[] arr, int begin, int end) {
        //先获取基准值
        getPivot(arr, begin, end);
        int pivot = arr[end - 1];
        int i = begin;
        int j = end - 1;
        while (i < j) {
            //先找到左区间中大于pivot的位置
            while (i < j && arr[++i] < pivot) {
            }
            while (i < j && arr[--j] >= pivot) {
            }
            swap(arr, i, j);
        }
        swap(arr, i, end - 1);
        return i;
    }
    //对三个位置进行排序
    public void getPivot(int[] arr, int begin, int end) {
        //对三个位置进行排序
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
        //将中间值放在数组倒数第二个位置
        swap(arr, mid, end - 1);
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,5,5,6};
        MySort mySort = new MySort();
        mySort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
