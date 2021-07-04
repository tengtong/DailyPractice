package dailypractice.m07;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1833. 雪糕的最大数量
 * @author tengtong
 */
public class M0702 {
    /**
     * 方法一：在拥有coins确定的情况下，能买到最高的雪糕数量
     * @param costs 一共n只雪糕，第i只雪糕对应的价格是costs[i]
     * @param coins 现金总量
     * @return
     */
    public int maxIceCream(int[] costs, int coins) {
        // 数组转list
        List<Integer> costsList = Arrays.stream(costs).boxed().collect(Collectors.toList());
        costsList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        // 购买雪糕的数量
        int count = 0;
        for (int cost : costsList) {
            if (coins >= cost) {
                coins -= cost;
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * 方法二：在方法一基础上，可以使用Arrays.sort(array)直接数组进行排序
     * @param costs 一共n只雪糕，第i只雪糕对应的价格是costs[i]
     * @param coins 现金总量
     * @return
     */
    public int maxIceCream1(int[] costs, int coins) {
        Arrays.sort(costs);
        // 购买雪糕的数量
        int count = 0;
        for (int i = 0; i < costs.length; i++) {
            int cost = costs[i];
            if (coins >= cost) {
                coins -= cost;
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] array = {1,3,2,4,1};
        M0702 test = new M0702();
        System.out.println(test.maxIceCream1(array, 7));
    }
}
