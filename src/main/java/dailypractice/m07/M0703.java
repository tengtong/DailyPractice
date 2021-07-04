package dailypractice.m07;

import java.util.*;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，按照出现的频率降序排列
 * @author tengtong
 */
public class M0703 {
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        // map是负责统计的数据结构
        Map<Character, Integer> map = new HashMap(s.length());
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        // list是负责排序的数据结构，负责对map.entrySet()进行排序
        // 同时，这里也可以以[1, maxFreQuency]为区间，使用桶排来排序
        List<Map.Entry<Character, Integer>> list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        // 最后按指定的要求进行输出
        StringBuilder resultString = new StringBuilder(s.length());
        for (Map.Entry<Character, Integer> entry : list) {
            int count = entry.getValue();
            while (count != 0) {
                resultString.append(entry.getKey());
                count--;
            }
        }
        return resultString.toString();
    }

    public static void main(String[] args) {
        String string  = "tree";
        M0703 test = new M0703();
        System.out.println(test.frequencySort(string));
    }
}
