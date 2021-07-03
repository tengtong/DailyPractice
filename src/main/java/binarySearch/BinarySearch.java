package binarySearch;


public class BinarySearch {

    //target不重复的二分查找
    public int Single(int[] arr, int target) {
        int len = arr.length;
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target < arr[mid]) {
                high = mid - 1;
            } else if (target > arr[mid]){
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    //有重复元素的二分查找
    public int NotSingle(int[] arr, int target) {
        int len = arr.length;
        int low = 0;
        int high = len - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            //这里取 <= 表示取重复target的最左边的，推导一下就可以得到答案
            if (target <= arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
