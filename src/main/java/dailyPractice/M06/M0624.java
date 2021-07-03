package dailyPractice.M06;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 149. 直线上最多的点数
public class M0624 {

    // 方法一：暴力枚举
    // 确定一条直线：y=kx+b，k和b可以确定一条直线
    // 思路：计算所有点之间的斜率k，map<pair<k,b>, Set<points>>
    // 通过率：23/33, 待修改
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        } else if (points.length == 1) {
            return 1;
        }
        // 点：points[i]
        //  map<pair<k,b>, Set<points>>
        // Set集合放的是不重复的点，Set<Pair<x,y>>
        Map<Pair<Integer, Integer>, Set<Pair<Integer, Integer>>> map = new HashMap();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                Pair pairKAndB = kAndB(x1, y1, x2, y2);
                Pair pairX1 = new Pair(x1, y1);
                Pair pairX2 = new Pair(x2, y2);
                if (map.containsKey(pairKAndB)) {
                    Set<Pair<Integer, Integer>> pointsSet = map.get(pairKAndB);
                    if (!pointsSet.contains(pairX1)) {
                        pointsSet.add(pairX1);
                    }
                    if (!pointsSet.contains(pairX2)) {
                        pointsSet.add(pairX2);
                    }
                    map.put(pairKAndB, pointsSet);
                } else {
                    Set<Pair<Integer, Integer>> pointsSet = new HashSet<>();
                    pointsSet.add(pairX1);
                    pointsSet.add(pairX2);
                    map.put(pairKAndB, pointsSet);
                }
            }
            Set set = new HashSet();
        }
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Pair<Integer, Integer>, Set<Pair<Integer, Integer>>> entry : map.entrySet()) {
            int curSize = entry.getValue().size();
            max = curSize > max ? curSize : max;
        }
        return max;
    }
    private Pair<Integer, Integer> kAndB (int x1, int y1, int x2, int y2) {
        // 分母不能为0
        // 斜率特殊情况，x=1，y=1
        // TODO
        if (x1 - x2 == 0) {
            return new Pair<>(x1, Integer.MAX_VALUE);
        } else if (y1 - y2 == 0) {
            return new Pair<>(Integer.MAX_VALUE, y1);
        } else {
            int k = (y1 - y2) / (x1 - x2);
            int b = y1 - k * x1;
            return new Pair<>(k, b);
        }
    }
    
    // 方法一：动态规划
    // 思路：区间型动态规划
    // dp[i]：以points[i]为结尾的区间上，在同一直线上最多的点数
    public int maxPoints1(int[][] points) {
        return 0;
    }
}
