package dailypractice.m05;

import java.util.*;
import java.util.stream.Collectors;

//554、砖墙
//初始思路：找出每个一维数组的前n项和的相同数最大的位置
//修正思路：当给定砖墙时，所有的砖块分布是固定的==所有的间隙位置是固定的
//        同时，穿过的砖最少==穿过的间隙最多，故只要统计出所有的间隙位置，然后找出现最多的位置，用总行数-间隙数=穿过的砖数
public class M0502 {
    public int leastBricks(List<List<Integer>> wall) {
        //统计所有的间隙位置
        //用map统计，key为间隙index，value为间隙出现的次数
        Map<Integer, Integer> map = new HashMap();
        int hangSum;
        for (List<Integer> hang : wall) {
            hangSum = 0;
            for (int i : hang) {
                hangSum += i;
                map.put(hangSum, map.getOrDefault(hangSum, 0) + 1);
            }
            //不能从两侧过，所以删除最后一项//因为墙每层的总数相同，故只有一条左边界和一条右边界，直接删除即可
            map.remove(hangSum);
        }
        //遍历map，找到出现次数最多的间隙位置，即找到value值最大的key
        //   或者也可以在存的时候就统计
        int max = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
            }
        }
        int ret = wall.size() - max;
        return ret;
    }

    public static void main(String[] args) {
        int[][] arrs = {{1,1}, {2}, {1,1}};
        List<List<Integer>> wall = new ArrayList<>();
        List list1 = Arrays.stream(arrs[0]).boxed().collect(Collectors.toList());
        List list2 = Arrays.stream(arrs[1]).boxed().collect(Collectors.toList());
        List list3 = Arrays.stream(arrs[2]).boxed().collect(Collectors.toList());
        wall.add(list1);
        wall.add(list2);
        wall.add(list3);
        M0502 test = new M0502();
        int ret = test.leastBricks(wall);
        System.out.println(ret);
    }
}
