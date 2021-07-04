package yanfazuiai;

//寻找第K大的元素，要求利用快排的思路
//   第K大的元素，不是第K小的元素，从大向小的排第K个
public class TopKth {
    public int findKth(int[] a, int n, int K) {
        int ret = findK(a, 0, a.length - 1,K);
        return ret;
    }
    public int findK(int[] arr, int begin, int end, int key) {
        //左区间当前有pivotIndex个元素
        if (begin <= end) {
            int pivotIndex = partition(arr, begin, end);
            if (pivotIndex == key - 1) {
                return arr[pivotIndex];
            } else if (pivotIndex < key - 1) {
                return findK(arr, pivotIndex + 1, end, key);
            } else if (pivotIndex > key - 1) {
                return findK(arr, begin, pivotIndex - 1, key);
            }
        }
        return -1;
    }

    private int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = begin;
        int j = end;
        while (i < j) {
            while (i < j && arr[i] > pivot) {
                i++;
            }
            arr[j] = arr[i];
            while (i < j && arr[j] <= pivot) {
                j--;
            }
            arr[i] = arr[j];
        }
        arr[j] = pivot;
        return j;
    }

    public static void main(String[] args) {
       int[] arr = {1,3,5,2,2};
       TopKth topKth = new TopKth();
       int ret = topKth.findKth(arr, arr.length, 3);
       System.out.println(ret);
    }
}
