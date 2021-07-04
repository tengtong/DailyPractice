package dailypractice.m07;

import javafx.util.Pair;

import java.util.*;

/**
 * LCP 07. 传递信息
 * @author tengtong
 */
public class M0701 {
    /**
     * 方法一：回溯，对走过的路标记一下
     * 0号玩家经过k轮传递到n-1号玩家的方案数
     * @param n n个玩家
     * @param relation 依赖关系
     * @param k 传递轮数
     * @return
     */
    public int numWays(int n, int[][] relation, int k) {
        // 将relation都存入到map，便于查询
        // key: incoming, value: List<Pair<outgoing, isPass>>
        Map<Integer, List<Pair<Integer, Boolean>>> map = new HashMap<>(n);
        for (int i = 0; i < relation.length; ++i) {
            int incoming = relation[i][0];
            int outgoing = relation[i][1];
            if (map.containsKey(incoming)) {
                List<Pair<Integer, Boolean>> outList = map.get(incoming);
                if (!outList.contains(outgoing)) {
                    outList.add(new Pair<>(outgoing, Boolean.FALSE));
                }
                map.put(incoming, outList);
            } else {
                List<Pair<Integer, Boolean>> outList = new LinkedList<>();
                outList.add(new Pair<>(outgoing, Boolean.FALSE));
                map.put(incoming, outList);
            }
        }
        // 标记过，也不需要排序了
        int result = backTrack(0, map, 0);
//        int result = 0;
//        for (int stepCount : resultList) {
//            if (stepCount == k) {
//                result++;
//            }
//        }
        return result;
    }

    /**
     * 从incoming到达n-1号的方案步数统计
     * @param incoming
     * @param map
     * @return 如果不能到达，则返回0
     */
    public int backTrack(int incoming, Map<Integer, List<Pair<Integer, Boolean>>> map, int lastNumCount) {
        // 在这里，2号位置会有两个返回值
        List<Pair<Integer, Boolean>> outgoingList = map.get(incoming);
        for (Pair<Integer, Boolean> outgoing : outgoingList){
            if (Boolean.FALSE.equals(outgoing.getValue())) {

            }
        }
        return 0;
    }

    /**
     * 方法二：dfs，记忆性搜索
     */
    int ways, n, k;
    List<List<Integer>> edges;

    public int numWays1(int n, int[][] relation, int k) {
        ways = 0;
        this.n = n;
        this.k = k;
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<Integer>());
        }
        for (int[] edge : relation) {
            int src = edge[0], dst = edge[1];
            edges.get(src).add(dst);
        }
        dfs(0, 0);
        return ways;
    }
    public void dfs(int index, int steps) {
        if (steps == k) {
            if (index == n - 1) {
                ways++;
            }
            return;
        }
        List<Integer> list = edges.get(index);
        for (int nextIndex : list) {
            dfs(nextIndex, steps + 1);
        }
    }
}
