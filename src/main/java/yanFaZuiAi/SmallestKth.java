package yanFaZuiAi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//最小的k个数
//思路1：排序，排完取出最小的k个
//思路2：小顶堆排序，推出k个
//思路3：快排思想
public class SmallestKth {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k > input.length || k == 0) {
            return new ArrayList<>();
        }
        ArrayList ret = new ArrayList();
        //默认是小根堆，内部是采用升序的比较器（可以记忆为先推出小的 再大的）
        //     大根堆，内部需要使用降序
        Queue queue = new PriorityQueue(new Comparator() {
            //升序：o1 - o2
            @Override
            public int compare(Object o1, Object o2) {
                return (int)o1 - (int)o2;
            }
        });
        for (int i = 0; i < input.length; i++) {
            queue.add(input[i]);
        }
        for (int i = 0; i < k; i++) {
            ret.add(queue.remove());
        }
        return ret;
    }
}
