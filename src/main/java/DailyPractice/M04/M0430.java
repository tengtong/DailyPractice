package DailyPractice.M04;

import java.util.HashMap;
import java.util.Map;

//137.只出现一次的数字II
//    无序
//思路1：hashMap统计，然后找出只出现一次的元素，空间换时间
//思路2：二进制，答案二进制的第i个位数为，所有元素该位置的和相加 % 3
//思路3：排序，然后只记录前后不重复的元素
//思路4：若其他找出两次，可以用异或碰撞抵消
public class M0430 {
    public int singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums) {
//            if (map.containsKey(i)) {
//                map.put(i, map.get(i) + 1);
//            } else {
//                map.put(i, 1);
//            }
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) {
                return e.getKey();
            }
        }
        return -1;
    }

    //二进制，ret的二进制表达式中在该位上的值为 所有元素该位上的值 % 3
    public int singleNumber2(int[] nums) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                //& 可以作为位运算符
                //(num >> i) & 1：判断该位置上是否为1
                //num << i：num数左移i位
                total += (num >> i) & 1;
            }
            total = total % 3;
            ret += total << i;
        }
        return ret;
    }
}
