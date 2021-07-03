package dailyPractice.M06;

import java.util.Deque;
import java.util.LinkedList;

// 494. 目标和
// 通过+、-使得mums数组中的元素，最终值为target
public class M0607 {

    // 方法一：可达性问题，dfs，普通栈
    public int findTargetSumWays(int[] nums, int target) {
//        int n = nums.length;
//        // 双向队列作栈
//        // 栈中放着累加值
//        Deque<Integer> stack = new LinkedList();
//        stack.push(nums[0]);
//        int subSum = 0;
//        int cur = 0;
//        while (!stack.isEmpty()) {
//            subSum += stack.pop();
//
//            if (subSum > target) {
//            }
//        }
        return 0;
    }

    // 方法一：dfs、递归栈
    private int count = 0;
    public int findTargetSumWays1(int[] nums, int target) {
        helper(nums, 0, 0, target);
        return count;
    }
    public void helper(int[] nums, int sum, int index, int target) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
            return;
        }
        // 这里要用index+1，而不是index++
        // 例如index=3时，index+1是将结果+1后，作为入参传给下一层递归函数，但在本层中index的值并没有变
        // 故在执行下一行语句时，index还是3。若index++，则对index本身的值进行了改变，则执行下一行时，index为4
        // 故不用在意helper方法中参数的执行顺序
        helper(nums, sum + nums[index], index + 1, target);
        helper(nums, sum - nums[index], index + 1, target);
    }

    // 方法二：动态规划
    public int findTargetSumWays2(int[] nums, int target) {
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        M0607 test = new M0607();
        System.out.println(test.findTargetSumWays1(nums, 3));
    }
}
