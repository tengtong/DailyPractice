package sort.swapSort;

import java.util.Arrays;

//冒泡排序
//思想：从最左侧向最右侧相邻比较排序，较大值移至最右边，这样第一遍排完，就找出了第一大的值
public class BubbleSort {
    public void solution(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,5,3,2,4,6};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.solution(arr);
        System.out.println(Arrays.toString(arr));
    }
}
