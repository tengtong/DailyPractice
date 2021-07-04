package dailypractice.m07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 645. 错误的集合
 * nums包含1-n，其中有一个重复，有一个丢失
 * @author tentong
 */
public class M0704 {
    /**
     * 方法一：先hashMap找出重复的元素，在通过相减找出丢失元素
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int repeatNum = 0;
        int missNum = 0;
        int sumNums = 0;
        int sumN = 0;
        // key：nums[i], value: 出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            sumN += i;
            sumNums += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        sumN += n;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 1) {
                repeatNum = entry.getKey();
                break;
            }
        }
        missNum = sumN - (sumNums - repeatNum);
        return new int[]{repeatNum, missNum};
    }

    /**
     * 方法二：hashMap直接在nums中统计1~n的元素出现次数，0就是missNum，2就是repeatNum；
     * @param nums
     * @return
     */
    public int[] findErrorNums1(int[] nums) {
        int n = nums.length;
        int repeatNum = 0;
        int missNum = 0;
        int[] result = new int[2];
        // key：nums[i], value: 出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i = 1; i <= n; ++i) {
            int count = map.getOrDefault(i, 0);
            if (count == 2) {
                result[0] = i;
            } else if (count == 0) {
                result[1] = i;
            }
        }
        return result;
    }

    /**
     * 方法三：排序，然后再对相邻元素进行判断
     * 但是当丢失和重复的是1或n时，即边界条件判断起来有点麻烦
     * @param nums
     * @return
     */
    public int[] findErrorNums2(int[] nums) {
        return null;
    }

    /**
     * 方法四：位运算
     * @param nums
     * @return
     */
    public int[] findErrorNums3(int[] nums) {
        return null;
    }

    public static void main(String[] args) {
        M0704 test = new M0704();
        int[] array = {1,2,2,4};
        int[] result = test.findErrorNums1(array);
        System.out.println(Arrays.toString(result));
    }
}
