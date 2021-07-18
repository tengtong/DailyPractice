package yanfazuiai;

/**
 * 二分查找
 * 找到重复数组中目标值出现的第一个位置下标
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;
        while (begin < end) {
            int mid = (begin + end) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,4,4,5};
        BinarySearch test = new BinarySearch();
        System.out.println(test.search(nums, 4));
    }
}
