package sort.selectsort;

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

    /**
     * 方法二：自己建堆（自己版）
     * 堆排
     * 堆本身就是一个数组
     * @param array
     */
    // 一个堆，只能保证父节点一定比左右节点大，但是左右节点之间的大小关系无法保证
    // 所以，堆排就是每次都把堆顶的元素和未排序数组的末尾元素进行交换，然后再堆内自排
    // 也就是通过“堆顶是堆中最大的元素”（大顶堆）来实习整个数组的排序
    public void heapSort(int[] array) {
        sort(array);
    }
    // 建堆，就是堆内自排，每次都把数组中最后一个元素和第一个元素交换，然后一次堆排
    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int lastIndex = array.length - 1 - i;
            // 先建堆，保证堆顶为当前未排序数组中的最大值
            buildHeap(array, 0, lastIndex);
            // 然后交换
            swap(array, 0, lastIndex);
        }
    }
    // 一次堆排
    // 正常的一次建堆，有两种方式，一种是从BottomToTop，另一种是TopToBottom，具体就不展开讲了
    // 这里因为是O(1)的空间复杂度，不能新增空间，所以是通过堆内自建
    // 即通过最后一个非父节点的节点开始，取当前父节点和其左右子节点的最大值作为其父节点
    // 然后依次往上排完所有父节点，就能保证根节点为当前堆中最大值
    public void buildHeap(int[] array, int begin, int end) {
        // 先找到一个非叶子节点
        int length = end - begin + 1;
        // 只有左节点
        int lastRoot = 0;
        if (length % 2 == 0) {
            lastRoot = (end - 1) / 2;
        } else {
            lastRoot = (end - 2) / 2;
        }
        for (int i = lastRoot; i >= 0; i--) {
            // 两次比较，找出最大值放在父节点上
            int leftIndex = 2* i + 1;
            int rightIndex = 2 * i + 2;
            if (array[i] < array[2 * i + 1]) {
                swap(array, i, leftIndex);
            }
            if (rightIndex <= length - 1) {
                if (array[i] < array[2 * i + 2]) {
                    swap(array, i, rightIndex);
                }
            }
        }
    }

    /**
     * 方法三：自己建堆（题解版）
     * @param arr
     */
    public void heapSort1(int[] arr) {
        buildHeap1(arr, arr.length);
        sort1(arr, arr.length);
    }
    public void buildHeap1(int[] arr, int n) {
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
    public void sort1(int[] arr, int n) {
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
        int[] arr = {1,7,3,6,8,2,4,5};
        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
