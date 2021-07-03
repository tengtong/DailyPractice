package search.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 组成整数的最少平方数数量
 * Input: 12
 * Output: 3 (12=4+4+4)
 * Input: 13
 * Output: 2 (13=9+4)
 * 思路：可以将每个整数看成图中的一个节点，如果两个整数之差为一个平方数，那么这两个整数所在的节点就有一条边
 *      要求解最小的平方数，就是求解从节点n到节点0的最短路径
 *      每个队列中存的都是只经过一次相减的next，这里以相减次数为步长来计算最少次的平方数数量
 *      这里也可以用动态规划求解
 *      最小/短==广度优先遍历
 */
public class LeastNumSquares {
    public int solution(int n){
        List<Integer> squares = generateSquares(n);//先获取小于n的所有平方数
        Queue<Integer> queue = new LinkedList<>();//队列中存放的都是当前一次步长的元素//队列都是用LinkedList，PriorityQueue是小顶堆
        boolean[] marked = new boolean[n+1];//标记数组
        queue.add(n);//载入第一个元素
        marked[n] = true;
        int level = 0;
        while (!queue.isEmpty()){//直到队列为空
            int size = queue.size();
            level++;
            while (size-- > 0){//每组队列中存的都是只经过一次相减的next
                int cur = queue.poll();
                //对队列中的next使用for循环来依次进行一次相减，求出下一组的next
                for (int s : squares){//依次取出可能的所有平方数
                    int next = cur - s;//cur是在上面取得，故每次循环的cur都是相同的，而计算后的next已经被add进queue中了//减去平方数剩余的值
                    if (next < 0){
                        break;
                    }
                    if (next == 0){//表示刚好减完
                        return level;
                    }
                    if (marked[next]){//如果减去后的数字已经被标记过（计算过），则跳过
                        continue;
                    }
                    marked[next] = true;//处理完当前数字，标记
                    queue.add(next);
                }
            }
        }
        return n;
    }

    /**
     * 生成小于n的平方数序列
     * 1.4.9.16...
     */
    private List<Integer> generateSquares(int n){
        List<Integer> squares = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n){
            squares.add(square);
            square += diff;
            diff += 2;
        }
        return squares;
    }
}
