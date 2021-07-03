package sort.selectSort;

import java.util.Arrays;

//直接选择排序
//思路：在未排序中选择出最大的，然后放在未排序的最右侧，即已排序的最左侧
//     相比冒泡排序需要比较和交换arr.length-1次，选择排序是直接找出最值，然后进行交换
public class SelectSort {
    public void sort(int[] arr) {
        // 第一个for循环表示执行i次，每次都选出第i大的值
        for (int i = 0; i < arr.length; i++) {
            int tempMax = arr[0];
            int tempIndex = 0;
            // 每次都在未排序数组中寻找出最大值，然后放置在未排序数组末端
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > tempMax) {
                    tempMax = arr[j];
                    tempIndex = j;
                }
            }
            this.swap(arr, tempIndex ,arr.length - i - 1);
        }
    }
    private void swap(int[] arr, int i ,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2,7,3,6,5};
        SelectSort selectSort = new SelectSort();
        selectSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
