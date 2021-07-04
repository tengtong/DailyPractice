package yanfazuiai;

import java.util.*;

//两数之和
//思路：hashmap
public class TwoSum {

    // 方法一：hashMap
    //       先将结果都存到hashMap中，然后只需要遍历一遍数组就可以了
    //       这里直接边遍历、边查询、边存
    public int[] twoSum (int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                int tempIndex = map.get(target - numbers[i]);
                if (i < tempIndex) {
                    return new int[]{i, tempIndex};
                } else {
                    return new int[]{tempIndex, i};
                }
            }
            map.put(numbers[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] arr = {3,2,4};
        int key = 6;
       int[] ret = twoSum.twoSum(arr, key);
        System.out.println(Arrays.toString(ret));
    }
}
