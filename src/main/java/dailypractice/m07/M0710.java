package dailypractice.m07;

import javafx.util.Pair;

import java.util.*;

/**
 * 981. 基于时间的键值存储
 * @author tengtong
 */
public class M0710 {

    /**
     * 方法一：hashMap + 时间戳排序
     * 比普通的map多一个时间的维度，时间戳只与key绑定
     * 具体的输入输出不用解析，只要写以下三个方法即可
     * 通过率：44/46，第45个超时
     */
    public class TimeMap {

        // key: foo, value: {<bar, 1>,<bar, 2>}
        private Map<String, List<Pair<String, Integer>>> map;

        public TimeMap() {
            map = new HashMap();
        }

        // 一般timestamp唯一，value不唯一
        public void set(String key, String value, int timestamp) {
            List<Pair<String, Integer>> valueList;
            if (map.containsKey(key)) {
                valueList = map.get(key);
            } else {
                valueList = new LinkedList<>();
            }
            valueList.add(new Pair<>(value, timestamp));
            map.put(key, valueList);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            } else {
                List<Pair<String, Integer>> list = map.get(key);
                list.sort(new Comparator<Pair<String, Integer>>() {
                    @Override
                    public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                        return o2.getValue() - o1.getValue();
                    }
                });
                String result = "";
                for (Pair<String, Integer> valuePair : list) {
                    int curTimeStamp = valuePair.getValue();
                    if (timestamp >= valuePair.getValue()) {
                        result = valuePair.getKey();
                        break;
                    }
                }
                return result;
            }
        }
        /**
         * Your TimeMap object will be instantiated and called as such:
         * TimeMap obj = new TimeMap();
         * obj.set(key,value,timestamp);
         * String param_2 = obj.get(key,timestamp);
         */
    }


    /**
     * 方法二：hashMap + 二分查找
     * 基本思路一致，在get最近时间戳的value时，选用二分，查找更快一些；方法一使用排序，然后倒序比较，存在超时
     */
    class TimeMap1 {
        class Pair implements Comparable<Pair> {
            int timestamp;
            String value;

            public Pair(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }

            public int hashCode() {
                return timestamp + value.hashCode();
            }

            public boolean equals(Object obj) {
                if (obj instanceof Pair) {
                    Pair pair2 = (Pair) obj;
                    return this.timestamp == pair2.timestamp && this.value.equals(pair2.value);
                }
                return false;
            }

            public int compareTo(Pair pair2) {
                if (this.timestamp != pair2.timestamp) {
                    return this.timestamp - pair2.timestamp;
                } else {
                    return this.value.compareTo(pair2.value);
                }
            }
        }

        Map<String, List<Pair>> map;

        public TimeMap1() {
            map = new HashMap<String, List<Pair>>();
        }

        public void set(String key, String value, int timestamp) {
            List<Pair> pairs = map.getOrDefault(key, new ArrayList<Pair>());
            pairs.add(new Pair(timestamp, value));
            map.put(key, pairs);
        }

        public String get(String key, int timestamp) {
            List<Pair> pairs = map.getOrDefault(key, new ArrayList<Pair>());
            // 使用一个大于所有 value 的字符串，以确保在 pairs 中含有 timestamp 的情况下也返回大于 timestamp 的位置
            Pair pair = new Pair(timestamp, String.valueOf((char) 127));
            int i = binarySearch(pairs, pair);
            if (i > 0) {
                return pairs.get(i - 1).value;
            }
            return "";
        }

        private int binarySearch(List<Pair> pairs, Pair target) {
            int low = 0, high = pairs.size() - 1;
            if (high < 0 || pairs.get(high).compareTo(target) <= 0) {
                return high + 1;
            }
            while (low < high) {
                int mid = (high - low) / 2 + low;
                Pair pair = pairs.get(mid);
                if (pair.compareTo(target) <= 0) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }
}
