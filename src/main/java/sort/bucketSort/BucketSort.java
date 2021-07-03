package sort.bucketSort;

import java.util.ArrayList;
import java.util.List;

//桶排序
//思路：找出数组中的最大值和最小值，然后根据桶的尺寸（个数），将这些数据放到桶中
//     然后从小桶开始到大桶，对桶中的所有元素再进行排序（可以为桶排，也可以为其他），然后依次输出到结果集中，完成排序
//特点：空间换时间，两者的复杂度都是O(n+k)
public class BucketSort {
    public List<Integer> sort(List<Integer> arr, int bucketSize) {
        if (arr == null || arr.size() < 2 || bucketSize < 1) {
            return arr;
        }
        int max = arr.get(0);
        int min = arr.get(0);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > max) {
                max = arr.get(i);
            }
            if (arr.get(i) < min) {
                min = arr.get(i);
            }
        }
        //创建桶
        int bucketCount = (max - min) / bucketSize + 1;
        List<List<Integer>> bucketList = new ArrayList<List<Integer>>();
        for (int i = 0; i < bucketCount; i++) {
            bucketList.add(new ArrayList<Integer>());
        }
        //将数组中的元素都存到桶中
        for (int j = 0; j < arr.size(); j++) {
            int bucketIndex = (arr.get(j) - min) / bucketSize;
            bucketList.get(bucketIndex).add(arr.get(j));
        }
        //再对每个桶进行排序，依次取出元素完成排序
        List<Integer> resultList = new ArrayList<Integer>();
        for (int j = 0; j < bucketList.size(); j++) {
            List<Integer> everyBucket = bucketList.get(j);
            if (everyBucket.size() > 0) {
                if (bucketCount == 1) {
                    bucketSize--;
                }
                //使用sort方法再对每个桶内的元素进行排序
                List<Integer> temp = sort(everyBucket, bucketSize);
                //将排完序的结果存入retList结果集中
                for (int i = 0; i < temp.size(); i++) {
                    resultList.add(temp.get(i));
                }
            }
        }
        return resultList;
    }
}









