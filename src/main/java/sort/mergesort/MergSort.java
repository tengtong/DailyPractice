package sort.mergesort;

import java.util.Arrays;

//归并排序
//思想：先拆，拆到底，只剩一个元素，再向上归并
//     即先向下sort，sort到底层时，开始返回，再逐层向上merge
public class MergSort {
    public int[] sort(int[] arr) {
        //sort(int[] arr, int begin ,int end), begin和end的主要作用就是为了确定mid，故不用也可以
        //递归要么放在子方法实现，要么放在原方法实现
        //截止条件，已经拆分成小数组，数组长度为1
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        //递归将大数组拆成小数组
        //方法一：用数组下标，对数组区间进行操作
        //方法二：创建新数组，进行赋值；
        //      大数组拆成小数组：Arrays.copyOfRange（这里需要创建无数多个小的数组，优化的话，可以每次使用同一个数组）
        //      小数组归并回大数组：merge子方法
        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid ,arr.length);
        //归并算法的核心思想：先拆，拆到底，只剩一个元素，再向上归并
        //先向下sort，sort到底层时，开始返回，再逐层向上merge
        int[] ret = merge(sort(leftArr), sort(rightArr));
        return ret;
    }
    //合并两个数组，合并后返回一个新数组
    private int[] merge(int[] leftArr, int[] rightArr) {
        int newArrLen = leftArr.length + rightArr.length;
        int leftIndex = 0, rightIndex = 0;
        int[] newArr = new int[newArrLen];
        for (int i = 0; i < newArrLen; i++) {
            if (leftIndex >= leftArr.length) {
                newArr[i] = rightArr[rightIndex++];
            } else if (rightIndex >= rightArr.length) {
                newArr[i] = leftArr[leftIndex++];
            } else if (leftArr[leftIndex] > rightArr[rightIndex]) {
                newArr[i] = rightArr[rightIndex++];
            } else {
                newArr[i] = leftArr[leftIndex++];
            }
        }
        return newArr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 7, 3, 6, 9, 2, 2, 4, 5};
        MergSort mergSort = new MergSort();//每个类都有一个默认的无参构造
        int[] newArr = mergSort.sort(arr);
        System.out.print(Arrays.toString(newArr));
    }
}
