package sort.insertsort;

import java.util.Arrays;

//希尔排序
//思路：希尔排序是插入排序的改良版，插入排序的插排间隙gap都为1，而希尔排序gap由到小
//    依次先从大范围实现插排，再小范围实现插排，以此实现时间复杂度的降低
public class ShellSort {
    public void sort(int[] arr) {
        int n = arr.length;
        //找到数组能存在的最大gap？怎么确定的gap
        int gap = n / 2;
        while (gap > 0) {
            //完成以当前gap为单位的一遍插排
            for (int i = gap; i < n; i++) {
                int current = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > current) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = current;
            }
            //缩减增量
            gap = gap / 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,4,2,7,3,6,5};
        ShellSort shellSort = new ShellSort();
        shellSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}