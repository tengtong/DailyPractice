package dailypractice.m04;

import java.util.Arrays;

//403.青蛙过河
//青蛙当前位于第i个石头上，上次跳跃距离为lastDis，则下次能跳跃的范围为[i+lastDis-1, i+lastDis+1]
//  那么去遍历这个范围内的石头是否存在，存在则进行一次递归dfs搜索
public class M0429 {

    //使用一个二维矩阵的两位状态信息 来记录dfs的走过路径的状态
    //如果使用一维数组，只能存rec[i]，即这个点是否走过的状态，没法表示一个路径状态
    //这里要用Boolean[][]来创建，不能使用boolean[][]数组来实现
    //   因为boolean[][]中不能存null值，必须要false或true，而在我们设计中false和true都是有含义的，为null表示没有进行记录
    private Boolean[][] rec;

    public boolean canCross(int[] stones) {
        int n = stones.length;
        rec = new Boolean[n][n];
        boolean ret = dfs(stones, 0, 0);
        return ret;
    }

    //dfs，表示当前i值出发有下一条路径，可达
    private boolean dfs(int[] stones, int i, int lastDis) {
        //终点状态
        if (i == stones.length - 1) {
            return true;
        }

        //历史路径状态
        if (rec[i][lastDis] != null) {
            return rec[i][lastDis];
        }

        //对当前位于i位置时，进行一次dfs搜索
        for (int curDis = lastDis - 1; curDis <= lastDis + 1; curDis++) {
            //先判断curDis>0，该跳跃计划是否可行
            if (curDis > 0) {
                //判断当前curDis在数组中是否存在
                int j = Arrays.binarySearch(stones, i + 1, stones.length, stones[i] + curDis);
                //如果存在下一个石头的位置j，再判断一下从j出发是否还有路径，若则当前路径可达，记为true
                if (j > 0 && dfs(stones, j , curDis)) {
                    //返回当前路径结果的同时，也记录在全部变量里，以减少重复搜索
                    return rec[i][curDis] = true;
                }
            }
        }
        //此路不同，若能到达i位置，但是后面没有路了
        return rec[i][lastDis] = false;
    }
}
