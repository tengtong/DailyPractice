package DailyPractice.M06;

// 1049. 最后一块石头的重量 II
public class M0608 {

    // 方法一：dfs
    // 思路：类同 494目标和 ，石头的碰撞可以，理解为给所有的数组元素前面加上'+'或'-'，然后求出数组中能计算出的最小值
    //      数组中所有元素加减后最靠近0的整数
    // 缺点：部分重复计算，且没有一些中间判断，剪枝之类的，容易超时
    private int minSum;
    public int lastStoneWeightII(int[] stones) {
        minSum = stones[0];
        helper(stones, 0, 0);
        return minSum;
    }
    public void helper(int[] nums, int sum, int index) {
        // 这个if是递归结束的条件，就是递归的出口，这是一定要能出去，不然递归内无限循环了
        if (index == nums.length) {
            if (sum >= 0) {
                minSum = sum < minSum ? sum : minSum;
            }
            return;
        }
        // 减枝
        if (sum == 0) {
            return;
        }
        helper(nums, sum + nums[index], index + 1);
        helper(nums, sum - nums[index], index + 1);
    }

    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        M0608 test = new M0608();
        System.out.println(test.lastStoneWeightII(stones));
    }
}
