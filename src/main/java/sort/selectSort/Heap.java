package sort.selectSort;

import java.util.Arrays;

public class Heap {
    //存储堆中元素的数组
    private int[] data;
    //堆中可存储数组的最大值
    private int size;
    //堆中已存元素的个数
    private int count;

    //初始化
    public Heap(int capacity) {
        this.data = new int[capacity + 1];
        this.size = capacity;
        this.count = 0;
    }

    @Override
    public String toString() {
        return "Heap{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", count=" + count +
                '}';
    }

    /**
     * insert方法
     * @param data
     */
    public void insert(int data) {
        if (count >= size) {
            return;
        }
        //往数组末端添加元素
        this.data[count++] = data;
        //堆化
        heapifyFromBottom2Top(this.data, count);
    }
    //在数组末尾添加了新元素，对新元素进行堆化整理
    //思路：从末端元素出发，沿着路径不断对比和交换，直至换到了比父节点小的位置上
    private void heapifyFromBottom2Top(int[] data, int end) {
        int i = end;
        //父节点: data[i / 2]，自身节点：data[i]
        while (i / 2 > 0 && this.data[i / 2] < this.data[i]) {
            swap(this.data, i / 2, i);
            i /= 2;
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * remove方法
     * @return
     */
    public int remove() {
        //remove堆顶元素
        int max = data[1];
        //将末尾元素交换至顶堆位置
        data[1] = data[count--];
        //对新堆进行堆化
        heapifyFromTop2Botton(data,1,count);
        return max;
    }
    //对顶位置移除，将数组末尾元素交换至堆顶，然后对"新堆顶"重新进行堆化整理
    //思路：和左右孩子节点进行比较，与两者中的较大值，进行交换
    private void heapifyFromTop2Botton(int[] data, int begin, int end) {
        while (true) {
            int maxPos = begin;
            if (2 * begin <= end && data[maxPos] < data[2 * begin]) {
                maxPos = 2 * begin;
            }
            if (2 * begin + 1 <= end && data[maxPos] < data[2 * begin + 1]) {
                maxPos = 2 * begin + 1;
            }
            if (begin == maxPos) {
                break;
            }
            swap(data, begin, maxPos);
            begin = maxPos;
        }
    }
}