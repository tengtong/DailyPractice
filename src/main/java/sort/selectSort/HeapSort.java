package sort.selectSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//堆排序，用的就是大顶堆，每次都推出当前堆中的最大值，以次完成排序
public class HeapSort {

    //方法一：用现有的堆
    public void priorityQueueSort(int[] arr) {
        Queue queue = new PriorityQueue(arr.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });//默认是小顶堆
        for (int i : arr) {
            queue.add(i);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(String.valueOf(queue.remove()));
        }
    }

    //方法二：自己建堆
    public void heapSort(int[] arr) {
        buildHeap(arr, arr.length);
        sort(arr, arr.length);
    }
    public void buildHeap(int[] arr, int n) {
        // 建堆时，采用的原地算法，不开辟新的空间，在自身数组上进行堆内排序完成建堆
        for (int i = n/2; i > 0; i--) {
            // i=n/2表示从第一个非叶子节点，从此开始依次对所有的父节点都进行排序检查，即i--
            heapifyFromTop2Botton(arr, i, n);
        }
    }
    // 在[begin, end]区间内完成自顶向下的堆内排序
    // 比较父节点是否比两个孩子节点都大，同时左边节点值是否小于右边
    // 建堆和出堆都用这个方法进行调整
    private void heapifyFromTop2Botton(int[] data, int begin, int end) {
        while (true) {
            int maxPos = begin;
            // 在堆中，索引值小的越靠近root节点
            // 如果左孩子节点>父节点，则交换
            if (2 * begin <= end && data[maxPos] < data[2 * begin]) {
                maxPos = 2 * begin;
            }
            // 如果右孩子节点>Math.max(父节点，右孩子节点)，则再交换
            if (2 * begin + 1 <= end && data[maxPos] < data[2 * begin + 1]) {
                maxPos = 2 * begin + 1;
            }
            // 直到一开始父节点就是三个节点中最大的，则停止排序
            if (begin == maxPos) {
                break;
            }
            swap(data, begin, maxPos);
            begin = maxPos;//交换完之后，以交换后的maxPos位置为起点（也就是新换下来的元素位置）开始新的一遍堆内排序
                           //   寻找该元素应该存在的位置，直至上面的begin=maxPos，也就是begin位置上就是最大的元素，即完成
        }
    }
    // 一次排序，交换堆顶和最后一位元素，然后对交换后的堆重新进行堆内排序，帮助找到"新堆顶"的正确位置
    // 交换n-1次元素，即完成了n-1次remove操作，完成了对所有元素的排序
    public void sort(int[] arr, int n) {
        while (n > 1) {
            //先进行依次排序
            swap(arr, 1, n);
            //然后对非末尾元素进行一次堆内自排序
            heapifyFromTop2Botton(arr, 1, n--);
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2,6,3,5};
        HeapSort heapSort = new HeapSort();
        heapSort.priorityQueueSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
