package dailyPractice.M05;

import java.util.*;

// 692. 前K个高频单词
public class M0520 {

    // 方法一：hashMap<String, Integer>存单词频率
    public List<String> topKFrequent(String[] words, int k) {
        // 结果集
        List<String> ret = new ArrayList<>(k);
        // 使用map来统计出现次数
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // 使用list对entry进行排序
        // 一个数据同时存储在两个数据结构中
        List<Map.Entry<String, Integer>> list = new LinkedList();
        list.addAll(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            // compare方法默认 o1-o2 为正序
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // 先比较单词出现的频率
                int Intdiff = (int)o2.getValue() - (int)o1.getValue();
                // 如果频率相同，再通过String自带的比较方法进行比较
                if (Intdiff == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return Intdiff;
                }
            }
        });
        for (int i = 0; i < k; i++) {
            ret.add(list.get(i).getKey());
        }
        return ret;
    }

    // 方法二：堆，边统计，边进行堆排
    //       本质还是类似，就是用map统计，然后再根据条件对map中的entry进行排序

    public static void main(String[] args) {
        String[] strings = {"i", "love", "leetcode", "i", "love", "coding"};
        M0520 test = new M0520();
        List<String> ret = test.topKFrequent(strings, 2);
        System.out.println(ret.toString());
    }
}
