package sort.InsertSort;

import java.util.Arrays;

// 插入排序
// 思路：数组分为已排序部分和未排序部分
//      待排序元素依次和已经排序的元素进行比较，如果待排序元素较小（小->大），则将当前比较已排序元素进行后移一位
//      直至待排序元素较大，则停止向前比较，将带排序元素放在当前的位置，重复如此
//     （因为这样会覆盖后待排序元素，所以一开始就用一个变量进行保存）
public class InsertSort {
    public void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return;
        }
        //开始排序
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];//待排序元素
            int preIndex = i - 1;
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];//将当前比较比较元素后移
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,4,2,7,3,6,5};
        InsertSort insertSort = new InsertSort();
        insertSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
